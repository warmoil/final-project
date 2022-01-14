package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.AuthUser;
import joy.world.fc.finalproject.api.dto.EngagementEmailStuff;
import joy.world.fc.finalproject.api.dto.EventCreateRequest;
import joy.world.fc.finalproject.core.dto.RequestStatus;
import joy.world.fc.finalproject.core.entity.Engagement;
import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.entity.User;
import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;
import joy.world.fc.finalproject.core.repository.EngagementRepository;
import joy.world.fc.finalproject.core.repository.ScheduleRepository;
import joy.world.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final EngagementRepository engagementRepository;
    private final EmailService emailService;

    @Transactional
    public void create(EventCreateRequest eventCreateRequest, AuthUser authUser) {
        //이벤트 참여자의 다른 이벤트와 중복이 되면안된다.
        // 1~2까지 회의가 있는데 , 추가로 다른 회의를 등록할수없음.
        //추가로 이메일 발송??!
        System.out.println(Thread.currentThread().getName());
        final List<Engagement> engagementList = engagementRepository.findAll(); //TODO findAll 추후 개선필요
        if (engagementList.stream()
                .anyMatch(e -> eventCreateRequest.getAttendeeIds().contains(e.getAttendee().getId())
                        && e.getRequestStatus() == RequestStatus.ACCEPTED
                        && e.getEvent().isOverlapped(
                        eventCreateRequest.getStartAt(),
                        eventCreateRequest.getEndAt())
                )) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(
                eventCreateRequest.getTitle(),
                eventCreateRequest.getDescription(),
                eventCreateRequest.getStartAt(),
                eventCreateRequest.getEndAt(),
                userService.findByUserId(authUser.getId())
        );
        scheduleRepository.save(eventSchedule);
        final List<User> attendees = eventCreateRequest.getAttendeeIds().stream()
                .map(userService::findByUserId)
                .collect(Collectors.toList());
        eventCreateRequest.getAttendeeIds()
                .forEach(id -> {
                    final User attendee = userService.findByUserId(id);
                    final Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee).build();
                    engagementRepository.save(engagement);
                    emailService.sendEngagement(EngagementEmailStuff.builder()
                            .title(engagement.getEvent().toGetTitle())
                            .toEmail(engagement.getAttendee().getEmail())
                            .engagementId(engagement.getId())
                            .attendeeEmails(attendees.stream().map(User::getEmail).collect(Collectors.toList()))
                            .period(engagement.getEvent().getPeriod())
                            .build());
                });
    }
}
