package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsDocumentTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsDocumentTagsRepository extends JpaRepository<DocumentsDocumentTags, Integer> {
}
