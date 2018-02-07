package com.prototype.restservice.transformer

import com.prototype.restservice.model.Sample
import com.prototype.restservice.model.SampleDto
import com.prototype.restservice.transformer.impl.SampleTransformerImpl
import spock.lang.Specification

class SampleTransformerSpec extends Specification {

    SampleTransformer sampleTransformer = new SampleTransformerImpl()

    def "Test transform"() {
        given:
        Sample sample = new Sample(
                id: 1L,
                text: "test"
        )

        when:
        SampleDto sampleDto = sampleTransformer.transform(sample)

        then:
        0 * _

        and:
        sampleDto.id == sample.id
        sampleDto.text == sample.text
    }

    def "Test transformDto"() {
        given:
        SampleDto sampleDto = new SampleDto(
                id: 1L,
                text: "test"
        )

        when:
        Sample sample = sampleTransformer.transformDto(sampleDto)

        then:
        0 * _

        and:
        sample.id == sampleDto.id
        sample.text == sampleDto.text
    }
}
