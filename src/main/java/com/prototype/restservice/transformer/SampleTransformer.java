package com.prototype.restservice.transformer;

import com.prototype.restservice.model.Sample;
import com.prototype.restservice.model.SampleDto;

public interface SampleTransformer {

    /**
     * Transforms a sample to a sample DTO.
     *
     * @param sample a sample
     * @return a sample DTO
     */
    SampleDto transform(Sample sample);
}
