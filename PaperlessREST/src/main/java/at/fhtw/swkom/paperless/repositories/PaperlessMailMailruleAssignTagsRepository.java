package at.fhtw.swkom.paperless.repositories;

import at.fhtw.swkom.paperless.entities.PaperlessMailMailruleAssignTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailMailruleAssignTagsRepository extends JpaRepository<PaperlessMailMailruleAssignTags, Integer> {
}
