package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsPaperlesstask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsPaperlesstaskRepository extends JpaRepository<DocumentsPaperlesstask, Integer> {
}
