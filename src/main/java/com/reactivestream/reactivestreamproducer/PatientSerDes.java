package com.reactivestream.reactivestreamproducer;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PatientSerDes implements Serializer<CovidPatient>, Deserializer<CovidPatient> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, CovidPatient person) {
        byte[] name = person.getName().getBytes(StandardCharsets.UTF_8);
        byte[] country = person.getCountry().getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(32 + name.length +  8 + country.length );
        buffer.putLong(person.getId());
        buffer.putInt(name.length);
        buffer.put(name);
        buffer.putInt(country.length);
        buffer.put(country);
        return buffer.array();
    }

    @Override
    public CovidPatient deserialize(String topic, byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        Long id = buffer.getLong();
        //name
        byte[] name = new byte[buffer.getInt()];
        buffer.get(name);
        String patientName = new String(name, StandardCharsets.UTF_8);
        //country
        byte[] country = new byte[buffer.getInt()];
        buffer.get(country);
        String patientCountry = new String(country, StandardCharsets.UTF_8);

        CovidPatient patient = new CovidPatient(id, patientName, patientCountry);
        return patient;
    }

    @Override
    public void close() {
    }
}
