package com.reactivestream.reactivestreamproducer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CovidPatient {
 private Long id;
 private String name;
 private String country;
}
