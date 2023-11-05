package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
}
