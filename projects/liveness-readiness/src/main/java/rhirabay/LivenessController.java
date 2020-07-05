package rhirabay;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/liveness")
public class LivenessController {
    private final ApplicationAvailability applicationAvailability;
    private final ApplicationEventPublisher eventPublisher;
    
    @GetMapping("/status")
    public Object getStatus() {
        return applicationAvailability.getLivenessState();
    }
    
    @GetMapping("/up")
    public Object up() {
        AvailabilityChangeEvent.publish(eventPublisher, "event source.", LivenessState.CORRECT);
        return this.getStatus();
    }

    @GetMapping("/down")
    public Object down() {
        AvailabilityChangeEvent.publish(eventPublisher, "event source.", LivenessState.BROKEN);
        return this.getStatus();
    }
}
