package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.DocumentsDocument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocument, Integer> {
}
