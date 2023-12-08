package com.kafka.producerapp.producer;


import com.kafka.producerapp.EmployeeVehicleDetails;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ProducerFactory;

import java.util.concurrent.ExecutionException;

public class EmployeeVehicleDetailsProducer {
    @Autowired
    private ProducerFactory<String, EmployeeVehicleDetails> employeePersonalVehicleProducerFactory;
    public void setEmployeePersonaldetail(EmployeeVehicleDetails employeeVehicleRequest) throws ExecutionException, InterruptedException {


        final KafkaProducer<String, EmployeeVehicleDetails> producer=new KafkaProducer<>(employeePersonalVehicleProducerFactory.getConfigurationProperties());
        // Rest of your logic here...

        EmployeeVehicleDetails employeeVehicleDetails= EmployeeVehicleDetails.newBuilder().setEmployeeId(employeeVehicleRequest.getEmployeeId()).setVehicleName(employeeVehicleRequest.getVehicleName()).setInsuranceDetail(employeeVehicleRequest.getInsuranceDetail()).setVehicleNumber(employeeVehicleRequest.getVehicleNumber()).setManafacturingYear(employeeVehicleRequest.getManafacturingYear()).build();
        ProducerRecord<String, EmployeeVehicleDetails> record = new ProducerRecord<>("Employee_vehicle_topic",
                String.valueOf(employeeVehicleDetails.getEmployeeId()), employeeVehicleDetails);

        System.out.println("Printing employeevehicleDetail Record:"+record);

        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger= LoggerFactory.getLogger(EmployeePersonalDetailProducer.class);
                if (e== null) {
                    logger.info("Successfully received the details as: \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset" + recordMetadata.offset() + "\n" +
                            "Timestamp" + recordMetadata.timestamp());
                }

                else {
                    logger.error("Can't produce,getting error",e);

                }
            }
        });
        producer.close();

    }

}
