package com.nonsuch1.rabbitmqTraining;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {
    private final static String QUEUE_NAME = "hello";
    private final static String TASK_QUEUE = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            boolean durable = true;
            channel.queueDeclare(TASK_QUEUE, durable, false, false, null);
            String message = String.join(" ", args);
            channel.basicPublish("", TASK_QUEUE, null, message.getBytes());
            System.out.println(" [X] Sent '" + message + "'");
        }
    }

}
