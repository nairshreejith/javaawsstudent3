package assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {ContextStackAutoConfiguration.class})
@ComponentScan(basePackages = {"assignment", "assignment.entity","assignment.controller","assignment.repository","assignment.service"})
public class UserApplication {
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
	
	
	@SqsListener("SudheerQueue")
    public void loadMessageFromSQS(String message) {
        // Process the received message here
        System.out.println("Received message from SQS queue: " + message);
    }
}
