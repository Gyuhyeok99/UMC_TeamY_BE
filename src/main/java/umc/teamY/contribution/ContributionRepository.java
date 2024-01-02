package umc.teamY.contribution;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    @Modifying
    @Query("UPDATE Contribution c SET c.count = c.count + 1 WHERE c.id = :contributionId")
    void incrementContribution(@Param("contributionId") Long contributionId);

    Optional<Contribution> findByUserUserIdAndProjectProjectId(Long ownerId, Long id);

    Optional<List<Contribution>> findByProjectProjectId(Long projectId);
}
