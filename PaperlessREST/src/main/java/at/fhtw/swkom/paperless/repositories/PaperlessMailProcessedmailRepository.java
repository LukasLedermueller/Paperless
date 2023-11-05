package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.PaperlessMailProcessedmail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlessMailProcessedmailRepository extends JpaRepository<PaperlessMailProcessedmail, Integer> {
}
