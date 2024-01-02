package umc.teamY.user;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.util.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String password;
    private String school;
    private String name;
    private Long contribution;
}
