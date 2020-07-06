package com.reactivestream.reactivestreamproducer;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Log4j2
@SpringBootApplication
public class ReactiveStreamProducerApplication {


	public static void main(String[] args) {
		//BlockHound.install();
		SpringApplication.run(ReactiveStreamProducerApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(PatientEventHandler patientEventHandler){
		return route(
				POST("/patient")
						.and(accept(MediaType.APPLICATION_JSON)),
				patientEventHandler::write
		);
	}
}

