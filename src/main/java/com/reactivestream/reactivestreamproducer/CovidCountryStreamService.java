package com.reactivestream.reactivestreamproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@Log4j2
@RequiredArgsConstructor
public class CovidCountryStreamService {
    private static final String TOPIC_NAME="rektopic";

    private final KafkaSender<Long, CovidPatient> kafkaSender;

    public Mono<Long> write(Mono<CovidPatient> patient) {
        log.info("{}",patient,"send to kafka");
        return kafkaSender
                .send(
                        patient.map(p ->
                                SenderRecord
                                        .create(
                                                new ProducerRecord<>(
                                                        TOPIC_NAME
                                                        , p
                                                ),
                                                p.getId()
                                        )
                        )
                )
                .next()
                .log()
                .map(metadata -> metadata.correlationMetadata());
    }

}
