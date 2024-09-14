package Job.Portal.System.producer;
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

//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendJsonMessage(String authPayLoad){
//
//        Message<String> message = MessageBuilder
//                .withPayload(authPayLoad)
//                .setHeader(KafkaHeaders.TOPIC,"validate_jwt")
//                .build();
//
//        log.info(format("Sending validate_jwt :: %s", authPayLoad));
//        kafkaTemplate.send(message);
//    }

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendJsonMessage(String msg){
        log.info(format("String message to Topic wikimedia-stream  :: %s",msg));
        kafkaTemplate.send("wikimedia-stream",msg);
    }
}
