package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.AuthUser;
import joy.world.fc.finalproject.api.dto.NotificationCreateRequest;
import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.entity.User;
import joy.world.fc.finalproject.core.repository.ScheduleRepository;
import joy.world.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(NotificationCreateRequest notificationCreateRequest, AuthUser authUser) {
        final User user = userService.findByUserId(authUser.getId());
        final List<LocalDateTime> notifyAtList = notificationCreateRequest.getRepeatTimes();
        notifyAtList.forEach(notifyAt -> {
            final Schedule notificationSchedule =
                    Schedule.notification(
                            notificationCreateRequest.getTitle(),
                            notifyAt,
                            user
                    );
            scheduleRepository.save(notificationSchedule);
        });
        /* final Schedule notificationSchedule =
                Schedule.notification(
                        notificationCreateRequest.getTitle(),
                        notificationCreateRequest.getNotifyAt()
                        , user);*/

    }
}
