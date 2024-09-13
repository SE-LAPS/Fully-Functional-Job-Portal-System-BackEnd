package Job.Portal.System.producer;
import Job.Portal.System.payload.AuthPayLoad;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaJsonProducer {

    private final KafkaTemplate<String, AuthPayLoad> kafkaTemplate;

    public void sendJsonMessage(AuthPayLoad authPayLoad){

        Message<AuthPayLoad> message = MessageBuilder
                .withPayload(authPayLoad)
                .setHeader(KafkaHeaders.TOPIC,"validate_jwt")
                .build();

        log.info(format("Sending validate_jwt :: %s", authPayLoad));
        kafkaTemplate.send(message);
    }
}
