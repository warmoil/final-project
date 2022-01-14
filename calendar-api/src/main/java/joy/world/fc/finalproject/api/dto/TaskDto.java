package joy.world.fc.finalproject.api.dto;

import joy.world.fc.finalproject.core.dto.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskDto implements ScheduleDto{
    private final Long scheduleId;
    private final LocalDateTime taskAt;
    private final String title;
    private final String description;
    private final Long writerId;
    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.TASK;
    }
}
