package top.alvinsite.framework.amqp;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.amqp.config.RabbitmqConfig;

import java.util.concurrent.TimeUnit;


/**
 * @author Alvin
 */
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        String result = (String) rabbitTemplate.convertSendAndReceive(RabbitmqConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        System.out.println(result);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
