//package com.kafka.producerapp.producer;
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
