package com.prototype.restservice.service

import com.prototype.restservice.model.Sample
import com.prototype.restservice.model.SampleDto
import com.prototype.restservice.repository.SampleRepository
import com.prototype.restservice.service.impl.SampleServiceImpl
import com.prototype.restservice.transformer.SampleTransformer
import spock.lang.Specification

class SampleServiceSpec extends Specification {

    SampleRepository sampleRepository = Mock(SampleRepository)
    SampleTransformer sampleTransformer = Mock(SampleTransformer)
    SampleService sampleService = new SampleServiceImpl(
            sampleRepository,
            sampleTransformer
    )

    def "Test getSample"() {
        given:
        Long id = 1L
        String test = "test"
        Sample sample = new Sample(
                id: id,
                text: test
        )
        SampleDto sampleDtoOut = new SampleDto(
                id: id,
                text: test
        )

        when:
        SampleDto sampleDto = sampleService.getSample(id)

        then:
        1 * sampleRepository.findOne(id) >> sample
        1 * sampleTransformer.transform(sample) >> sampleDtoOut
        0 * _

        and:
        sampleDto.id == id
        sampleDto.text == test
    }

    def "Test getAllSamples"() {
        given:
        Sample sample1 = new Sample(
                id: 1L,
                text: "test_1"
        )
        Sample sample2 = new Sample(
                id: 2L,
                text: "test_2"
        )
        List<Sample> samples = [sample1, sample2]
        SampleDto sampleDto1 = new SampleDto(
                id: 1L,
                text: "test_1"
        )
        SampleDto sampleDto2 = new SampleDto(
                id: 2L,
                text: "test_2"
        )

        when:
        List<SampleDto> sampleDtos = sampleService.getAllSamples()

        then:
        1 * sampleRepository.findAll() >> samples
        1 * sampleTransformer.transform(samples[0]) >> sampleDto1
        1 * sampleTransformer.transform(samples[1]) >> sampleDto2
        0 * _

        and:
        sampleDtos
        sampleDtos[0].id == sample1.id
        sampleDtos[0].text == sample1.text
        sampleDtos[1].id == sample2.id
        sampleDtos[1].text == sample2.text
    }

    def "Test addSample"() {
        given:
        Long id = 1L
        String text = "test"
        SampleDto sampleDto = new SampleDto(
                text: text
        )
        Sample sample = new Sample(
                text: text
        )
        Sample sampleAdded = new Sample(
                id: id,
                text: text
        )
        SampleDto sampleDtoAdded = new SampleDto(
                id: id,
                text: text
        )

        when:
        Long sampleID = sampleService.addSample(sampleDto)

        then:
        1 * sampleTransformer.transformDto(sampleDto) >> sample
        1 * sampleRepository.save(sample) >> sampleAdded
        1 * sampleTransformer.transform(sampleAdded) >> sampleDtoAdded
        0 * _

        and:
        sampleID == id
    }

    def "Test deleteSample"() {
        given:
        Long id = 1L

        when:
        sampleService.deleteSample(id)

        then:
        1 * sampleRepository.delete(id)
        0 * _
    }
}
