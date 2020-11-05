package top.alvinsite.framework.amqp;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author Alvin
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public String receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
        return "OK";
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
