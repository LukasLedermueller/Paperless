package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
}
