package mvest.common.event.payload;

import lombok.*;
import mvest.common.event.EventPayload;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEventPayload implements EventPayload {
    private Long userId;
    private LocalDateTime registeredAt;
}
