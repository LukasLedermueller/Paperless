package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.DocumentsDocument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocument, Integer> {
}
