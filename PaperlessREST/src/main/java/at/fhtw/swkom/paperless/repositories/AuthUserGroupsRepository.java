package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.models.AuthUserGroups;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroups, Integer> {
}
