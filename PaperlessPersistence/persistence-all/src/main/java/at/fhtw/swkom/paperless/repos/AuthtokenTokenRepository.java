package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthtokenToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenToken, Long> {
}
