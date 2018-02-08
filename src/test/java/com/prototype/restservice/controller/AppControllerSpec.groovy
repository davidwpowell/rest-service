package com.prototype.restservice.controller

import com.prototype.restservice.model.SampleDto
import com.prototype.restservice.service.SampleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Ignore
import spock.lang.Specification

class AppControllerSpec extends Specification {

    SampleService sampleService = Mock(SampleService)
    AppController appController = new AppController(
            sampleService
    )

    def "Test index"() {
        when:
        ResponseEntity<String> response = appController.index()

        then:
        0 * _

        and:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "ALIVE"
    }

    def "Test getSample"() {
        given:
        String id = "1"
        SampleDto sampleDto = new SampleDto()

        when:
        ResponseEntity<SampleDto> response = appController.getSample(id)

        then:
        1 * sampleService.getSample(Long.valueOf(id)) >> sampleDto
        0 * _

        and:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == sampleDto
    }

    def "Test getAllSamples"() {
        given:
        SampleDto sampleDto1 = new SampleDto()
        SampleDto sampleDto2 = new SampleDto()
        List<SampleDto> sampleDtos = [sampleDto1, sampleDto2]

        when:
        ResponseEntity<List<SampleDto>> response = appController.getAllSamples()

        then:
        1 * sampleService.getAllSamples() >> sampleDtos
        0 * _

        and:
        response.getStatusCode() == HttpStatus.OK
        response.getBody()[0] == sampleDto1
        response.getBody()[1] == sampleDto2
    }

    // TODO: fix spec
    @Ignore
    def "Test createSample"() {
        given:
        Long id = 1L
        SampleDto sampleDto = new SampleDto(
                text: "test"
        )

        when:
        ResponseEntity<?> response = appController.createSample(sampleDto)

        then:
        1 * sampleService.addSample(sampleDto) >> id
        0 * _

        and:
        response.getStatusCode() == HttpStatus.CREATED
        response.getHeaders().getLocation().toString() == "samples/id/${id}"
    }

    def "Test updateSample"() {
        given:
        String id = "1"
        SampleDto sampleDto = new SampleDto(
                text: "test"
        )

        when:
        ResponseEntity<?> response = appController.updateSample(id, sampleDto)

        then:
        1 * sampleService.updateSample(Long.valueOf(id), sampleDto)
        0 * _

        and:
        response.getStatusCode() == HttpStatus.NO_CONTENT
    }

    def "Test deleteSample"() {
        given:
        String id = "1"

        when:
        ResponseEntity<?> response = appController.deleteSample(id)

        then:
        1 * sampleService.deleteSample(Long.valueOf(id))
        0 * _

        and:
        response.getStatusCode() == HttpStatus.NO_CONTENT
    }
}
