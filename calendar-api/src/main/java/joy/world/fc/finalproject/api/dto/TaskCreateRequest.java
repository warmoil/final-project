package joy.world.fc.finalproject.api.dto;

import joy.world.fc.finalproject.core.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateRequest {
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;
    private final User writer;
}
