package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.AuthUserUserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissions, Integer> {
}
