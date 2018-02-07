package com.prototype.restservice.transformer.impl;

import com.prototype.restservice.model.Sample;
import com.prototype.restservice.model.SampleDto;
import com.prototype.restservice.transformer.SampleTransformer;
import org.springframework.stereotype.Service;

@Service
public class SampleTransformerImpl implements SampleTransformer {

    @Override
    public SampleDto transform(final Sample sample) {
        SampleDto sampleDto = new SampleDto();
        sampleDto.setId(sample.getId());
        sampleDto.setText(sample.getText());
        return sampleDto;
    }

    @Override
    public Sample transformDto(final SampleDto sampleDto) {
        Sample sample = new Sample();
        sample.setId(sampleDto.getId());
        sample.setText(sampleDto.getText());
        return sample;
    }
}
