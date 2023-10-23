package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.DocumentsStoragepath;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsStoragepathRepository extends JpaRepository<DocumentsStoragepath, Integer> {
}
