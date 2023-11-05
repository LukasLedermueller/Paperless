package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthUserUserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissions, Integer> {
}
