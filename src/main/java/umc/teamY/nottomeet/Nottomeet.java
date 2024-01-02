package umc.teamY.nottomeet;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.user_project.UserProject;
import umc.teamY.global.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Nottomeet extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProject userProject;
}
