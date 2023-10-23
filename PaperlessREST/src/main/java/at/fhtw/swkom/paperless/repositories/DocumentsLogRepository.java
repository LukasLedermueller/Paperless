package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.DocumentsLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsLogRepository extends JpaRepository<DocumentsLog, Integer> {
}
