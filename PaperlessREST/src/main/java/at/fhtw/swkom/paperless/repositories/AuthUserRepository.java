package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
}
