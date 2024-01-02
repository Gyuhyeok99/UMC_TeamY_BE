package umc.teamY.todo;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.metting.Metting;
import umc.teamY.util.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Metting metting;
}
