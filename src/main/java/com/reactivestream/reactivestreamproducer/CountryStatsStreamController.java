package com.reactivestream.reactivestreamproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log4j2
@RestController
@RequiredArgsConstructor
class CountryStatsStreamController {

	private final KafkaService kafkaService;

	@GetMapping(value = "/country/{country}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<CovidPatient>> countryStream(@PathVariable("country") String country) {

		return kafkaService
				.getEventPublisher()
				.map(sse -> sse.data())
				.filter(patient -> patient.getCountry().equals(country))
				.map(p ->
						ServerSentEvent
								.builder(p)
								.build()
				);
	}

}

