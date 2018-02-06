package com.prototype.restservice.controller

import com.prototype.restservice.model.SampleDto
import com.prototype.restservice.service.SampleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class AppControllerSpec extends Specification {

    SampleService sampleService = Mock(SampleService)
    AppController appController = new AppController(
            sampleService: sampleService
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
}
