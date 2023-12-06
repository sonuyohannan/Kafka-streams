package com.kafka.producerapp.producer;//package com.kafka.producerapp.producer;
//
//import com.kafka.producerapp.EmployeePersonalDetails;
//import jakarta.websocket.SendResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProducerConfig {
//    @Autowired
//    private KafkaTemplate<String, EmployeePersonalDetails> kafkaTemplate;
//
//    public void sendMessage(EmployeePersonalDetails message) {
//        kafkaTemplate.send("Employee_personal_topic",String.valueOf(EmployeePersonalDetails.newBuilder().getEmployeeId()), message);
//
//
//    }
//
//}


import com.kafka.producerapp.EmployeePersonalDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Configuration
public class EmployeeDetailTemplate {

    @Bean
    public KafkaTemplate<String, EmployeePersonalDetails> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public DefaultKafkaProducerFactory<String, EmployeePersonalDetails> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        config.put("schema.registry.url", "http://localhost:8081");

        return new DefaultKafkaProducerFactory<>(config);
    }

    public void sendMessageToKafka(EmployeePersonalDetails employeePersonalDetail) throws ExecutionException, InterruptedException {
        System.out.println("employee Template inside :"+employeePersonalDetail.toString());
        KafkaTemplate<String, EmployeePersonalDetails> kafkaTemplate = kafkaTemplate();
        kafkaTemplate.send("Employee_personal_topic", String.valueOf(employeePersonalDetail.getEmployeeId()), employeePersonalDetail).get();
        // Add error handling and other logic as needed
    }
}
