package com.kafka.producerapp.consumer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.kafka.producerapp.EmployeeAdressDetails;
import com.kafka.producerapp.EmployeePersonalDetails;
import com.kafka.producerapp.EmployeeVehicleDetails;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class EmployeePersonalDetailConsumer {
    @Bean
    public void setConsumerProperties() {
        System.out.println("Consumer invoked");
        String groupId="myGroup";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url","http://localhost:8081");
        properties.setProperty("group.id",groupId);

        KafkaConsumer<String, EmployeePersonalDetails> consumer=new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("EmployeePersonalDetails", "EmployeeAdressDetails", "EmployeeVehicleDetails"));
        while(true) {
            ConsumerRecords<String, EmployeePersonalDetails> record = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String,EmployeePersonalDetails> records: record){
                System.out.println(records.value());
            }
        }
    }
}
