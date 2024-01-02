package umc.teamY.user_project;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.project.Project;
import umc.teamY.user.User;
import umc.teamY.util.BaseTimeEntity;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UserProject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
    private Project project;
}
