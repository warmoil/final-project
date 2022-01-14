package joy.world.fc.finalproject.core.repository;

import joy.world.fc.finalproject.core.dto.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Long> {
    
}
