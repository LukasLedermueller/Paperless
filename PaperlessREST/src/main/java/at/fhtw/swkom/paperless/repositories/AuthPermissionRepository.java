package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
