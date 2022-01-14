package joy.world.fc.finalproject.api.util;

import joy.world.fc.finalproject.api.dto.EventDto;
import joy.world.fc.finalproject.api.dto.NotificationDto;
import joy.world.fc.finalproject.api.dto.ScheduleDto;
import joy.world.fc.finalproject.api.dto.TaskDto;
import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;

public abstract class DtoConverter {
    public static ScheduleDto fromSchedule(Schedule schedule) {
        switch (schedule.getScheduleType()) {
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .taskAt(schedule.getStartAt())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .title(schedule.getTitle())
                        .build();
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .scheduleId(schedule.getId())
                        .build();
            default:
                throw new CalendarException(ErrorCode.VALIDATION_FAIL);
        }

    }
}
