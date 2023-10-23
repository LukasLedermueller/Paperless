package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.AuthtokenToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenToken, Long> {
}
