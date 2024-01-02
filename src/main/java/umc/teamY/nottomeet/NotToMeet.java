package umc.teamY.nottomeet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.global.BaseTimeEntity;
import umc.teamY.nottomeet.schedule.Schedule;
import umc.teamY.user_project.UserProject;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class NotToMeet extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_project_id")
    private UserProject userProject;

    private String date;

    @OneToMany(mappedBy = "notToMeet")
    private List<Schedule> schedules;

    public static NotToMeet of(UserProject userProject, String date) {
        return NotToMeet.builder()
                .userProject(userProject)
                .date(date)
                .schedules(new ArrayList<>())
                .build();
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }
}
