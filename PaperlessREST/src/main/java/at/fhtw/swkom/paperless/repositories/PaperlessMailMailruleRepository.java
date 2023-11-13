package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.PaperlessMailMailrule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailMailruleRepository extends JpaRepository<PaperlessMailMailrule, Integer> {
}
