package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.AuthUser;
import joy.world.fc.finalproject.api.dto.ScheduleDto;
import joy.world.fc.finalproject.api.util.DtoConverter;
import joy.world.fc.finalproject.core.repository.EngagementRepository;
import joy.world.fc.finalproject.core.repository.ScheduleRepository;
import joy.world.fc.finalproject.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    public List<ScheduleDto> getScheduleByDay(AuthUser authUser, LocalDate date) {
        return byPeriod(authUser, Period.of(date, date));
    }

    public List<ScheduleDto> getScheduleByWeek(AuthUser authUser, LocalDate startOfWeek) {
        return byPeriod(authUser, Period.of(startOfWeek, startOfWeek.plusDays(6)));
    }

    public List<ScheduleDto> getScheduleByMonth(AuthUser authUser, YearMonth yearMonth) {
        return byPeriod(authUser, Period.of(yearMonth.atDay(1),yearMonth.atEndOfMonth()));
    }

    private List<ScheduleDto> byPeriod(AuthUser authUser, Period period) {
        return Stream
                .concat(
                        scheduleRepository.findAllByWriter_id(authUser.getId())
                                .stream()
                                .filter(schedule -> schedule.isOverlapped(period))
                                .map(DtoConverter::fromSchedule),
                        engagementRepository.findAllByAttendee_Id(authUser.getId())
                                .stream()
                                .filter(engagement -> engagement.isOverlapped(period))
                                .map(engagement -> DtoConverter.fromSchedule(engagement.getSchedule()))
                ).collect(Collectors.toList());
    }
}
