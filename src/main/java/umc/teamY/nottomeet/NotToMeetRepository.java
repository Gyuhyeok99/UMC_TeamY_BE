package umc.teamY.nottomeet;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotToMeetRepository extends JpaRepository<NotToMeet, Long>{
    Optional<NotToMeet> findByUserProjectProjectIdAndDate(Long projectId, String date);
}
