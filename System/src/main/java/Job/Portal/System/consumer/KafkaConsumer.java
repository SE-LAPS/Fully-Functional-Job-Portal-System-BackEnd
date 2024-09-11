package Job.Portal.System.consumer;

import Job.Portal.System.payload.AuthResult;
import com.SpringReactive.kafka.payload.Student;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final AuthResult authResult;

    @KafkaListener(topics = "validate_jwt", groupId = "myGroup")
    public boolean consumeJsonMsg(){
        log.info(format("Consuming the Json message from validate_jwt :: %s",authResult));
        return authResult.isResult();
    }
}
