package Job.Portal.System.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.common.protocol.types.Field;

import java.util.UUID;

@Getter
@Setter
@ToString
public class AuthPayLoad {
    UUID id;
    String token;
}
