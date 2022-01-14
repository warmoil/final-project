package joy.world.fc.finalproject.core.dto;


import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Notification {
    Schedule schedule;

    public Notification(Schedule schedule) {
        this.schedule = schedule;
    }
}
