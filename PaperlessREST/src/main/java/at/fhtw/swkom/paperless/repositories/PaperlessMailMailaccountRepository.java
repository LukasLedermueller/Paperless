package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.PaperlessMailMailaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailMailaccountRepository extends JpaRepository<PaperlessMailMailaccount, Integer> {
}
