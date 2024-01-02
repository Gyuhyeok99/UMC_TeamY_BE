package umc.teamY.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager entityManager;

//    @Test
//    void incrementContribution() {
//        User user = new User(1L, "studentId", "pw", "school", "name", 0L);
//        userRepository.save(user);
//        userRepository.incrementContribution(user.getId());
//
//        entityManager.flush();
//        entityManager.clear();
//
//        User updatedUser = userRepository.findById(user.getId()).orElseThrow();
//
//        Assertions.assertThat(updatedUser.getContribution()).isEqualTo(1L);
//    }
}