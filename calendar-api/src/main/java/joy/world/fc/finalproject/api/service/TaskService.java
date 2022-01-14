package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.AuthUser;
import joy.world.fc.finalproject.api.dto.TaskCreateRequest;
import joy.world.fc.finalproject.core.entity.Schedule;
import joy.world.fc.finalproject.core.repository.ScheduleRepository;
import joy.world.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public void create(TaskCreateRequest request, AuthUser authUser) {
        final Schedule taskSchedule =
                Schedule.task(request.getTitle(),
                        request.getDescription(),
                        request.getTaskAt(),
                        userService.findByUserId(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }
}
