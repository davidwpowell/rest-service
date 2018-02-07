package com.prototype.restservice.service;

import com.prototype.restservice.model.SampleDto;

import java.util.List;

public interface SampleService {

    /**
     * Returns a sample record.
     *
     * @param id a sample id
     * @return a sample DTO
     */
    SampleDto getSample(Long id);

    /**
     * Returns a list of all sample records.
     *
     * @return a list of sample DTOs
     */
    List<SampleDto> getAllSamples();

    /**
     * Creates a new sample record.
     *
     * @param sampleDto a sample DTO
     * @return a generated sample id
     */
    Long addSample(SampleDto sampleDto);
}
