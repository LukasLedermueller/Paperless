package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
