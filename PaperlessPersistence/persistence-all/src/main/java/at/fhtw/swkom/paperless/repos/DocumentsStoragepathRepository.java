package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.DocumentsStoragepath;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsStoragepathRepository extends JpaRepository<DocumentsStoragepath, Integer> {
}
