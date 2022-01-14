package joy.world.fc.finalproject.core.repository;

import joy.world.fc.finalproject.core.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    List<Engagement> findAllByAttendee_Id(Long id);

    Optional<Engagement> findById(Long id);
}
