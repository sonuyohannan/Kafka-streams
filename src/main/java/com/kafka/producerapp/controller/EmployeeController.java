package com.kafka.producerapp.controller;


import com.kafka.producerapp.EmployeePersonalDetails;
import com.kafka.producerapp.producer.EmployeeDetailTemplate;
import com.kafka.producerapp.producer.EmployeePersonalDetailProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("api/kafka")
public class EmployeeController {
    static Logger logger = LoggerFactory.getLogger(EmployeePersonalDetailProducer.class);


    @Autowired
    private EmployeePersonalDetailProducer employeePersonaldetailProducer;
    @Autowired
    private EmployeeDetailTemplate employeeDetailTemplate;





    @PostMapping(value = "/employeepersonal")
    public void sendSchemaRegistryToKafkaTopic(@RequestBody EmployeePersonalDetails request) throws ExecutionException, InterruptedException {
//        EmployeePersonalDetails employeeDetail =   EmployeePersonalDetails.newBuilder()
//                .setEmployeeId(request.getEmployeeId())
//                .setFirstname(request.getFirstname())
//                .setLastname(request.getLastname()).setAge(request.getAge())
//                .setSex(request.getSex())
//                .build();
        logger.info("employeepersonal request:",String.valueOf(request));
        this.employeePersonaldetailProducer.setEmployeePersonaldetail(request);


    }

    @PostMapping(value = "/employeepersonalrequest")
    public void sendMessage(@RequestBody EmployeePersonalDetails request) throws ExecutionException, InterruptedException {
        EmployeePersonalDetails employeeDetail =   EmployeePersonalDetails.newBuilder()
                .setEmployeeId(request.getEmployeeId())
                .setFirstname(request.getFirstname())
                .setLastname(request.getLastname()).setAge(request.getAge())
                .setSex(request.getSex())
                .build();
        logger.info("employeepersonal Template request:",String.valueOf(employeeDetail));
        this.employeeDetailTemplate.sendMessageToKafka(employeeDetail);


    }

}
