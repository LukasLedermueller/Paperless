package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
}
