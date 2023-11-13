package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.AuthtokenToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenToken, Long> {
}
