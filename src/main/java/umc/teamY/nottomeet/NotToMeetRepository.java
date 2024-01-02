package umc.teamY.nottomeet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotToMeetRepository extends JpaRepository<NotToMeet, Long>{

}
