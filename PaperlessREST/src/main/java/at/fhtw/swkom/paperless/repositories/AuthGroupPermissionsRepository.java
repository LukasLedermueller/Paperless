package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.AuthGroupPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupPermissionsRepository extends JpaRepository<AuthGroupPermissions, Integer> {
}
