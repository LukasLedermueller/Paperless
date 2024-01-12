package at.fhtw.swkom.paperless.services.elasticsearch;

import at.fhtw.swkom.paperless.config.ElasticSearchConfig;
import at.fhtw.swkom.paperless.exceptions.ElasticSearchException;
import at.fhtw.swkom.paperless.services.dto.Document;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class ElasticSearchService implements SearchIndexService {
    private final ElasticsearchClient esClient;

    @Autowired
    public ElasticSearchService(ElasticsearchClient esClient) throws IOException {
        this.esClient = esClient;

        if (!esClient.indices().exists(
                i -> i.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
        ).value()) {
            esClient.indices().create(c -> c
                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
            );
        }
    }

    @Override
    public Result indexDocument(Document document) throws IOException {
        // do indexing with ElasticSearch
        IndexResponse response = esClient.index(i -> i
                .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                .id(document.getId().toString())
                .document(document)
        );
        String logMsg = "Indexed document " + document.getId() + ": result=" + response.result() + ", index=" + response.index();
        if ( response.result()!=Result.Created && response.result()!=Result.Updated )
            log.error("Failed to " + logMsg);
        else
            log.info(logMsg);
        return response.result();
    }

    @Override
    public Optional<Document> getDocumentById(int id) {
        try {
            GetResponse<Document> response = esClient.get(g -> g
                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                            .id(String.valueOf(id)),
                    Document.class
            );
            return (response.found() && response.source()!=null) ? Optional.of(response.source()) : Optional.empty();
        } catch (IOException e) {
            log.error("Failed to get document id=" + id + " from elasticsearch: " + e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteDocumentById(int id) {
        DeleteResponse result = null;
        try {
            result = esClient.delete(d -> d.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME).id(String.valueOf(id)));
        } catch (IOException e) {
            log.warn("Failed to delete document id=" + id + " from elasticsearch: " + e);
        }
        if (result == null)
            return false;
        if (result.result() != Result.Deleted)
            log.warn(result.toString());
        return result.result() == Result.Deleted;
    }

    @Override
    public List<Integer> searchDocuments(String searchString) throws ElasticSearchException {
        List<Integer> hits = new ArrayList<>();
        try {
            List<String> fields = new ArrayList<>(Arrays.asList("title", "content", "original_file_name"));
            for(String field: fields) {
                hits.addAll(getHits(field, searchString));
            }
            HashSet<Integer> uniqueSet = new HashSet<>(hits);
            List<Integer> hitsWithoutDuplicates = new ArrayList<>(uniqueSet);
            return hitsWithoutDuplicates;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ElasticSearchException(e.getMessage());
        }
    };

    private List<Integer> getHits(String field, String searchString) throws Exception {
        try {
            List<Integer> hitsToReturn = new ArrayList<>();
            SearchResponse<Document> response = esClient.search(s -> s
                            .index("documents")
                            .query(q -> q
                                    .match(m -> m
                                            .field(field)
                                            .query(searchString)
                                    )
                            ),
                    Document.class
            );
            List<Hit<Document>> hits = response.hits().hits();
            for (Hit<Document> hit : hits) {
                Document document = hit.source();
                log.info("Found document " + document.getId() + ", score " + hit.score() + " with field " + field);
                hitsToReturn.add(document.getId());
            }
            return hitsToReturn;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
