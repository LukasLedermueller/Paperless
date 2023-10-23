package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.PaperlessMailProcessedmail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlessMailProcessedmailRepository extends JpaRepository<PaperlessMailProcessedmail, Integer> {
}
