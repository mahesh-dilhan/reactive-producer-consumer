package com.reactivestream.reactivestreamproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Log4j2
@RequiredArgsConstructor
@Component
public class PatientEventHandler {
    private final CovidCountryStreamService covidCountryStreamService;

    public Mono<ServerResponse> write(ServerRequest serverRequest) {
        var person = serverRequest.bodyToMono(CovidPatient.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        covidCountryStreamService.write(person),
                        Long.class
                );
    }
}
