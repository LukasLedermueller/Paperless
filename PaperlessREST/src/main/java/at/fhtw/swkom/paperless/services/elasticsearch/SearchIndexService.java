package at.fhtw.swkom.paperless.services.elasticsearch;

import at.fhtw.swkom.paperless.exceptions.ElasticSearchException;
import at.fhtw.swkom.paperless.services.dto.Document;
import co.elastic.clients.elasticsearch._types.Result;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException;

    Optional<Document> getDocumentById(int id);

    boolean deleteDocumentById(int id);

    List<Integer> searchDocuments(String searchString) throws ElasticSearchException;

}
