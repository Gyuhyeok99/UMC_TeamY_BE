package umc.teamY.nottomeet.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Time;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.nottomeet.NotToMeet;
import umc.teamY.user.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "not_to_meet_id")
    private NotToMeet notToMeet;

    private Time startTime;

    private Time endTime;

    public static Schedule of(User user, NotToMeet notToMeet, Time startTime, Time endTime) {
        return Schedule.builder()
                .user(user)
                .notToMeet(notToMeet)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
