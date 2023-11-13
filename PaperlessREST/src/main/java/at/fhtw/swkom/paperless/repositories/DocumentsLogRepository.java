package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsLogRepository extends JpaRepository<DocumentsLog, Integer> {
}
