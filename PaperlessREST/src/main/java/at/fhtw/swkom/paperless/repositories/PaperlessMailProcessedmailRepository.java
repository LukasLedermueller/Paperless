package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.PaperlessMailProcessedmail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlessMailProcessedmailRepository extends JpaRepository<PaperlessMailProcessedmail, Integer> {
}
