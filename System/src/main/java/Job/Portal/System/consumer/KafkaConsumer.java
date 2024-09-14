package Job.Portal.System.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {

    //private final AuthResult authResult;

//    @KafkaListener(topics = "validate_jwt_result", groupId = "authGroup")
//    public AuthResult consumeJsonMsg(){
//        log.info(format("Consuming the Json message from validate_jwt :: %s",authResult));
//        return authResult;
//    }

    @KafkaListener(topics = "validate_jwt_result", groupId = "authGroup")
    public String consumeMsg(String msg){
        log.info(format("Consuming the message from chamithTopic :: %s",msg));
        return "true";
    }

}
