package at.fhtw.swkom.paperless.repos;

import at.fhtw.swkom.paperless.domain.AuthUserGroups;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroups, Integer> {
}
