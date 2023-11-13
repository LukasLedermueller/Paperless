package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.DocumentsSavedviewfilterrule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsSavedviewfilterruleRepository extends JpaRepository<DocumentsSavedviewfilterrule, Integer> {
}
