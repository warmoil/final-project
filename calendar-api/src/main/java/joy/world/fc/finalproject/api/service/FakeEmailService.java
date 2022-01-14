package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.EngagementEmailStuff;
import joy.world.fc.finalproject.core.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService{

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println("send email . email:" + stuff.getSubject());
    }
}
