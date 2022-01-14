package joy.world.fc.finalproject.api.controller;

import joy.world.fc.finalproject.api.dto.*;
import joy.world.fc.finalproject.api.service.*;
import joy.world.fc.finalproject.core.dto.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final ScheduleQueryService queryService;
    private final EngagementService engagementService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(
            @RequestBody TaskCreateRequest request,
            HttpSession session,
            AuthUser authUser
    ) {
        taskService.create(request, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(
            @Valid  @RequestBody EventCreateRequest request,
            HttpSession session,
            AuthUser authUser
    ) {
        eventService.create(request, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createNotifications(
            @RequestBody NotificationCreateRequest notificationCreateRequest,
            HttpSession session,
            AuthUser authUser
    ) {
        notificationService.create(notificationCreateRequest, authUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/day")
    public List<ScheduleDto> getScheduleByDay(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date) {
        return queryService.getScheduleByDay(authUser, date == null ? LocalDate.now() : date);
    }
    @GetMapping("/week")
    public List<ScheduleDto> getScheduleByWeek(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate startOfWeek) {
        return queryService.getScheduleByWeek(authUser, startOfWeek == null ? LocalDate.now() : startOfWeek);
    }
    @GetMapping("/month")
    public List<ScheduleDto> getScheduleByMonth(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM")  String yearMonth) {
        return queryService.getScheduleByMonth(authUser, yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth));
    }


    @PutMapping("/events/engagements/{engagementId}")
    public RequestStatus updateEngagement(
            @Valid @RequestBody ReplyEngagementRequest replyEngagementRequest,
            @PathVariable Long engagementId,
            AuthUser authUser
    ) {
        return engagementService.update(authUser, engagementId, replyEngagementRequest.getType());
    }

}
