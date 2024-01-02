package umc.teamY.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByStudentId(String studentId);
    Optional<User> findByStudentId(String studentId);

    @Modifying
    @Query("UPDATE User u SET u.contribution = u.contribution + 1 WHERE u.id = :userId")
    void incrementContribution(@Param("userId") Long userId);
}

