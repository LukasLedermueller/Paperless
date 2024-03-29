package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.config.ElasticSearchConfig;
import at.fhtw.swkom.paperlessservices.exceptions.ElasticSearchException;
import at.fhtw.swkom.paperlessservices.services.dto.Document;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

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
    public Result indexDocument(Document document) throws ElasticSearchException {
        try {
            IndexResponse response = esClient.index(i -> i
                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                    .id(document.getId().toString())
                    .document(document)
            );
            String logMsg = "Indexed document " + document.getId() + ": result=" + response.result() + ", index=" + response.index();
            if (response.result() != Result.Created && response.result() != Result.Updated)
                throw new Exception("Failed to " + logMsg);
            else
                log.info(logMsg);
            return response.result();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ElasticSearchException(e.getMessage());
        }
    }

    @Override
    public Optional<Document> getDocumentById(int id) throws ElasticSearchException {
        try {
            GetResponse<Document> response = esClient.get(g -> g
                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                            .id(String.valueOf(id)),
                    Document.class
            );
            return (response.found() && response.source()!=null) ? Optional.of(response.source()) : Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException("Failed to get document id=" + id + " from elasticsearch: " + e);
            // return Optional.empty();
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
        if ( result==null )
            return false;
        if (result.result() != Result.Deleted )
            log.warn(result.toString());
        return result.result()==Result.Deleted;
    }

}
