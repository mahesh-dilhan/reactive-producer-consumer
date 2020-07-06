package com.reactivestream.reactivestreamproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private static final String TOPIC_NAME="rektopic";
    /************************************************************************
     * Please refer project reactor for different Kafka configuration setup *
     ************************************************************************/


    private SenderOptions<Long, CovidPatient> senderOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "reactive-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PatientSerDes.class);
        props.put(ProducerConfig.RETRIES_CONFIG, 2);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "5000");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "100");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");
        return SenderOptions.create(props);
    }

    private KafkaSender<Long, CovidPatient> kafkaSender;

    @Bean
    public KafkaSender<Long, CovidPatient> kafkaSender() {
        this.kafkaSender= KafkaSender.create(senderOptions());
        return kafkaSender;
    }

    public ReceiverOptions<Long, CovidPatient> receiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "reactive-group-id");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactive-client");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PatientSerDes.class);
        return ReceiverOptions.<Long, CovidPatient>create(props);
    }

    public ReceiverOptions<Long, CovidPatient> receiverOptions(Collection<String> topics) {
        return receiverOptions()
                .addAssignListener(p -> log.info("Group {} partitions assigned {}", "reactive-group-id", p))
                .addRevokeListener(p -> log.info("Group {} partitions assigned {}", "reactive-group-id", p))
                .subscription(topics);
    }

    @Bean
    public KafkaReceiver<Long, CovidPatient> kafkaReceiver(){
        return  KafkaReceiver.create(receiverOptions(Arrays.asList(TOPIC_NAME)));
    }

    public void close() {
        if (kafkaSender != null)
            kafkaSender.close();
    }

}
