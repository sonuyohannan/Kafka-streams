package com.kafka.producerapp.producer;

import com.kafka.producerapp.EmployeeAdressDetails;
import com.kafka.producerapp.EmployeePersonalDetails;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ProducerFactory;

import java.util.concurrent.ExecutionException;

public class EmployeeAddressDetailProducer {
    @Autowired
    private ProducerFactory<String, EmployeeAdressDetails> employeePersonalAddressProducerFactory;

    public void setEmployeePersonaldetail(EmployeeAdressDetails employeeadressdetailRequest) throws ExecutionException, InterruptedException {


        final KafkaProducer<String, EmployeeAdressDetails> producer=new KafkaProducer<>(employeePersonalAddressProducerFactory.getConfigurationProperties());
        // Rest of your logic here...

        EmployeeAdressDetails employeeAdressDetails= EmployeeAdressDetails.newBuilder().setEmployeeId(employeeadressdetailRequest.getEmployeeId()).setHousename(employeeadressdetailRequest.getHousename()).setCity(employeeadressdetailRequest.getCity()).setPostcode(employeeadressdetailRequest.getPostcode()).setStreetname(employeeadressdetailRequest.getStreetname()).build();
        ProducerRecord<String, EmployeeAdressDetails> record = new ProducerRecord<>("Employee_address_topic",
                String.valueOf(employeeAdressDetails.getEmployeeId()), employeeAdressDetails);

        System.out.println("Printing employeeaddressDetail Record:"+record);

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
