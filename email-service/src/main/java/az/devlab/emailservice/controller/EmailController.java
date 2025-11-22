package az.devlab.emailservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Value("${email.max-retries}")
    private int maxRetries;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body) {

        StringBuilder sb = new StringBuilder();
        sb.append("Sending email to ").append(to).append("\n");
        sb.append("Subject: ").append(subject).append("\n");
        sb.append("Body: ").append(body).append("\n");
        sb.append("Simulating retries:\n");

        for (int i = 1; i <= maxRetries; i++) {
            sb.append("  Attempt ").append(i).append("\n");
        }

        sb.append("Finished. Max retries from config: ").append(maxRetries);
        return sb.toString();
    }
}
