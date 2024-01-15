package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocument, Integer> {
    @Modifying
    @Query("update DocumentsDocument d set d.archiveFilename = ?1 where d.id = ?2")
    void setArchiveFilename(String archiveFilename, Integer id);
}
