package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthGroupPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupPermissionsRepository extends JpaRepository<AuthGroupPermissions, Integer> {
}
