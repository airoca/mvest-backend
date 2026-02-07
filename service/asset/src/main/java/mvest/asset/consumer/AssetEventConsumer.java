package mvest.asset.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvest.asset.application.AssetService;
import mvest.common.event.Event;
import mvest.common.event.EventPayload;
import mvest.common.event.EventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AssetEventConsumer {

    private final AssetService articleReadService;

    @KafkaListener(topics = {
            EventType.Topic.MVEST_ASSET,
            EventType.Topic.USER_REGISTERED,
            EventType.Topic.USER_WITHDRAWN
    })
    public void listen(String message, Acknowledgment ack) {
        log.info("[ArticleReadEventConsumer.listen] message={}", message);
        Event<EventPayload> event = Event.fromJson(message);
        if (event != null) {
            articleReadService.handleEvent(event);
        }
        ack.acknowledge();
    }
}
