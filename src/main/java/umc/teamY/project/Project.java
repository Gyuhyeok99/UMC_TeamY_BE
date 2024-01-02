package umc.teamY.project;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.global.BaseTimeEntity;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ProjectTerm projectTerm;
}
