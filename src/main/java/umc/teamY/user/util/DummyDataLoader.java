package umc.teamY.user.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.teamY.user.User;
import umc.teamY.user.UserRepository;

@Service
@RequiredArgsConstructor
public class DummyDataLoader {

    private final UserRepository userRepository;

    @PostConstruct
    public void loadDummyData() {
        userRepository.save(User.builder()
                .studentId("S1")
                .password("password1")
                .school("SchoolA")
                .name("test1")
                .contribution(100L)
                .build());

        userRepository.save(User.builder()
                .studentId("S2")
                .password("password2")
                .school("SchoolB")
                .name("test2")
                .contribution(150L)
                .build());
        }
}
