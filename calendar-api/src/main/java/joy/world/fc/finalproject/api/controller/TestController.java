package joy.world.fc.finalproject.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final JavaMailSender emailSender;

    @GetMapping("/api/mail")
    public void sendTestMail(@RequestParam String title) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("jipmj12@naver.com");
            helper.setSubject(title);
            helper.setText("이것은 스프링으로 코딩해서 보내는 이메일이오");
        };
        emailSender.send(preparator);
    }

    @GetMapping("test/template")
    public String testTemplate(Model model) {
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "타이틀입니다");
        props.put("calendar", "jipmj12@naver.com");
        props.put("period", "언제부터 언제까지");
        props.put("attendees", List.of("jipmj12@naver.com", "jipmj12@gmail.com"));
        props.put("acceptUrl", "http://localhost:8080");
        props.put("rejectUrl", "http://localhost:8080");
        model.addAllAttributes(props);
        return "engagement-email";
    }

}

