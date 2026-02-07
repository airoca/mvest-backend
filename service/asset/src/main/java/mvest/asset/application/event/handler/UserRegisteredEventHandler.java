package mvest.asset.application.event.handler;

import lombok.RequiredArgsConstructor;
import mvest.common.event.Event;
import mvest.common.event.EventType;
import mvest.common.event.payload.UserRegisteredEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisteredEventHandler implements EventHandler<UserRegisteredEventPayload> {

    @Override
    public void handle(Event<UserRegisteredEventPayload> event) {
        UserRegisteredEventPayload payload = event.getPayload();
    }

    @Override
    public boolean supports(Event<UserRegisteredEventPayload> event) {
        return EventType.USER_REGISTERED == event.getType();
    }
}
