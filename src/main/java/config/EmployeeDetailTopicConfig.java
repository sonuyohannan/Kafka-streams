package config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class EmployeeDetailTopicConfig {
    @Bean
    public NewTopic EmployeePersonalTopicConfig(){
        return TopicBuilder.name("Employee_peronal_topic").build();
    }

    @Bean
    public NewTopic EmployeeAddressTopicConfig(){
        return TopicBuilder.name("Employee_address_topic").build();
    }

    @Bean
    public NewTopic EmployeeVehicleTopicConfig(){
        return TopicBuilder.name("Employee_vehicle_topic").build();
    }
}
