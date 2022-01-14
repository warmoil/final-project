package joy.world.fc.finalproject.core.entity;

import joy.world.fc.finalproject.core.dto.Event;
import joy.world.fc.finalproject.core.dto.RequestReplyType;
import joy.world.fc.finalproject.core.dto.RequestStatus;
import joy.world.fc.finalproject.core.util.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Builder
public class Engagement extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "envent_id")
    private Schedule schedule;
    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Event getEvent() {
        return schedule.toEvent();
    }

    public Engagement(Schedule schedule, User attendee, RequestStatus requestStatus) {
        this.schedule = schedule;
        this.attendee = attendee;
        this.requestStatus = requestStatus;
    }


    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }

    public Engagement reply(RequestReplyType type) {
        switch (type) {
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.requestStatus = RequestStatus.REJECTED;
                break;
        }
        return this;
    }
}
