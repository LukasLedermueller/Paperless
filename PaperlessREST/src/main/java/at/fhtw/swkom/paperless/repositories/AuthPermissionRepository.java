package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
