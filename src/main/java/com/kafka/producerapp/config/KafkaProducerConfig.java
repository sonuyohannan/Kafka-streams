package com.kafka.producerapp.config;
import com.kafka.producerapp.EmployeeAdressDetails;
import com.kafka.producerapp.EmployeePersonalDetails;
import com.kafka.producerapp.EmployeeVehicleDetails;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
@Configuration
public class KafkaProducerConfig {
@Bean
public ProducerFactory<String, EmployeePersonalDetails> employeePersonalDetailsProducerFactory() {
        Map<String, Object> configProps = producerConfigProperties();
        return new DefaultKafkaProducerFactory<>(configProps);
        }

    @Bean
    public ProducerFactory<String, EmployeeVehicleDetails> employeePersonalVehicleProducerFactory() {
        Map<String, Object> configProps = producerConfigProperties();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, EmployeeAdressDetails> employeePersonalAddressProducerFactory() {
        Map<String, Object> configProps = producerConfigProperties();
        return new DefaultKafkaProducerFactory<>(configProps);
    }



private Map<String, Object> producerConfigProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081");

    Map<String, Object> configProps = new HashMap<>();
    properties.forEach((key, value) -> configProps.put((String) key, value));
    return configProps;
        }
}


