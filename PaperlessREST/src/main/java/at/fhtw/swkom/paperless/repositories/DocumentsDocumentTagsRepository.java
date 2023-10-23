package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.DocumentsDocumentTags;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentTagsRepository extends JpaRepository<DocumentsDocumentTags, Integer> {
}
