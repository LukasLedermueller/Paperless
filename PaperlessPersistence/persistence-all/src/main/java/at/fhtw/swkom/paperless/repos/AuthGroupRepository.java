package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}
