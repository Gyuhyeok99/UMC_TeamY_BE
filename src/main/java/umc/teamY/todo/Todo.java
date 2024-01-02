package umc.teamY.todo;

import jakarta.persistence.*;
import lombok.*;
import umc.teamY.meeting.Meeting;
import umc.teamY.global.BaseTimeEntity;
import umc.teamY.tag.Tag;

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
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void assignOnwer(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void updateTodoCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
