package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.exceptions.ElasticSearchException;
import at.fhtw.swkom.paperlessservices.services.dto.Document;
import co.elastic.clients.elasticsearch._types.Result;

import java.io.IOException;
import java.util.Optional;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException, ElasticSearchException;

    Optional<Document> getDocumentById(int id) throws ElasticSearchException;

    boolean deleteDocumentById(int id);
}
