package com.prototype.restservice.service.impl;

import com.prototype.restservice.model.Sample;
import com.prototype.restservice.model.SampleDto;
import com.prototype.restservice.repository.SampleRepository;
import com.prototype.restservice.service.SampleService;
import com.prototype.restservice.transformer.SampleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;
    private final SampleTransformer sampleTransformer;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository, SampleTransformer sampleTransformer) {
        this.sampleRepository = sampleRepository;
        this.sampleTransformer = sampleTransformer;
    }

    @Override
    public SampleDto getSample(final Long id) {
        Sample sample = sampleRepository.findOne(id);
        return sampleTransformer.transform(sample);
    }

    @Override
    public List<SampleDto> getAllSamples() {
        List<Sample> samples = sampleRepository.findAll();
        List<SampleDto> sampleDtos = samples.stream()
                .map(sampleTransformer::transform)
                .collect(Collectors.toList());
        return sampleDtos;
    }

    @Override
    public Long addSample(final SampleDto sampleDto) {
        Sample sample = sampleTransformer.transformDto(sampleDto);
        Sample sampleAdded = sampleRepository.save(sample);
        return sampleTransformer.transform(sampleAdded).getId();
    }
}
