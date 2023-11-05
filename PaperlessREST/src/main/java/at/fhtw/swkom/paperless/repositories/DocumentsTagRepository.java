package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
}
