package assignment.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;

@ContextConfiguration(classes = SQSConfigTest.Config.class)
public class SQSConfigTest {

    @BeforeEach
    public void setUp() {
        // Set up any necessary mock behavior here
    }

    @Test
    public void testQueueMessagingTemplate() {
        // Create an application context to load the SQSConfig
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
            // Retrieve the QueueMessagingTemplate bean
            QueueMessagingTemplate queueMessagingTemplate = context.getBean(QueueMessagingTemplate.class);
            
            // Ensure that the QueueMessagingTemplate is not null
            assertNotNull(queueMessagingTemplate);
        }
    }

    @Configuration
    @ComponentScan(basePackages = "assignment.service")
    @PropertySource("classpath:application.properties")
    static class Config {
    }
}