package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.EngagementEmailStuff;
import joy.world.fc.finalproject.core.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff emailStuff);
}
