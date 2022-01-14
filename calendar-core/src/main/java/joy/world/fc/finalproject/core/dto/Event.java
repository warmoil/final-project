package joy.world.fc.finalproject.core.dto;

import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.entity.User;
import joy.world.fc.finalproject.core.util.Period;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Event {
    private final Schedule schedule;
    public Event(Schedule schedule) {
        this.schedule = schedule;
    }
    public Long getId() {
        return this.schedule.getId();
    }
    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }


    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }

    public String getTitle() {
        return schedule.getTitle();
    }

    public String toGetTitle() {
        return this.schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(this.schedule.getStartAt(), this.schedule.getEndAt());
    }
}
