package Job.Portal.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "Job.Portal.System")
public class JobPortalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalSystemApplication.class, args);
	}

}
