package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}
