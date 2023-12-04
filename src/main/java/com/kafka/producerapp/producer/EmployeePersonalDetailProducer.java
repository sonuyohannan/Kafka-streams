package com.kafka.producerapp.producer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.kafka.producerapp.EmployeePersonalDetails;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class EmployeePersonalDetailProducer {

    static Logger logger = LoggerFactory.getLogger(EmployeePersonalDetailProducer.class);


    public void setEmployeePersonaldetail(EmployeePersonalDetails employeePersonaldetailRequest) throws ExecutionException, InterruptedException {


        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url","http://localhost:8080");
        final KafkaProducer<String, EmployeePersonalDetails> producer=new KafkaProducer<String, EmployeePersonalDetails>(properties);
        EmployeePersonalDetails employeePersonalDetail= EmployeePersonalDetails.newBuilder().setFirstname(employeePersonaldetailRequest.getFirstname()).setEmployeeId(employeePersonaldetailRequest.getEmployeeId()).setAge(employeePersonaldetailRequest.getAge()).setLastname(employeePersonaldetailRequest.getLastname()).setSex(employeePersonaldetailRequest.getSex()).build();
        System.out.println("Printing employeePersonalDetail after value setting:"+ employeePersonalDetail.toString());

        ProducerRecord<String, EmployeePersonalDetails> record = new ProducerRecord<>("Employee_personal_topic",
                String.valueOf(employeePersonalDetail.getEmployeeId()), employeePersonalDetail);
        logger.info("record printing",String.valueOf(record));
        System.out.println("Printing employeePersonalDetail Record:"+record);
        producer.send(record).get();




    }




}
