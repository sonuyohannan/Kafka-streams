package producer;

import com.kafka.producerapp.EmployeePersonalDetails;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class EmployeePersonalDetailProducer {
    public static  void main(String args[]) {
        // create property objects

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("acks", "all");
        props.put("retries", 0);
        props.setProperty("key.serializer", StringSerializer.class.getName());

        props.put("value.serializer",
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);

        final KafkaProducer<String, EmployeePersonalDetails> producer=new KafkaProducer<String, EmployeePersonalDetails>(props);
        EmployeePersonalDetails employeePersonalDetail= EmployeePersonalDetails.newBuilder().setFirstname("sonu").setEmployeeId(1).setAge(25).setLastname("yohannan").setSex("male").build();
        System.out.println("student builder");

        ProducerRecord<String, EmployeePersonalDetails> record = new ProducerRecord<>("Employee_personal_topic",
                String.valueOf(employeePersonalDetail.getEmployeeId()), employeePersonalDetail);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("Record sent successfully");
                    System.out.println(metadata.toString());
                } else {
                    System.out.println("Error while sending record");
                    exception.printStackTrace();
                }
            }
        });
        // Close the producer when done sending messages
        producer.close();
    }

}
