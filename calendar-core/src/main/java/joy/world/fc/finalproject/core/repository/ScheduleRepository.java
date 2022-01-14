package joy.world.fc.finalproject.core.repository;

import joy.world.fc.finalproject.core.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findAllByWriter_id(Long id);
}
