package com.reactivestream.reactivestreamproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.kafka.receiver.KafkaReceiver;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class KafkaService {

    /********************************************
     * building stream of Hot Publisher
     ********************************************/

    private final KafkaReceiver<Long, CovidPatient> kafkaReceiver;

    private ConnectableFlux<ServerSentEvent<CovidPatient>> eventPublisher;

    @PostConstruct
    public void init() {
        eventPublisher = kafkaReceiver
                .receive()
                .map(consumerRecord ->
                        ServerSentEvent
                                .builder(
                                        consumerRecord.value()
                                )
                                .build()
                )
                .publish();
        eventPublisher.connect();
    }

    public ConnectableFlux<ServerSentEvent<CovidPatient>> getEventPublisher() {
        return eventPublisher;
    }

}
