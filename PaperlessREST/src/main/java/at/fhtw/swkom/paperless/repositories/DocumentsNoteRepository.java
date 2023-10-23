package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.DocumentsNote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsNoteRepository extends JpaRepository<DocumentsNote, Integer> {
}
