package joy.world.fc.finalproject.core;

import joy.world.fc.finalproject.core.dto.ScheduleType;
import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainCreateTest {

    @Test
    void eventTest() {
        final User me = new User("mine", "hello", "pw", LocalDate.now());
        final Schedule taskSchedule = Schedule.task("할일", "청소", LocalDateTime.now(),me);

        assertEquals(taskSchedule.getScheduleType(), ScheduleType.TASK);
        assertEquals(taskSchedule.toTask().getTitle(), "할일");

    }

}