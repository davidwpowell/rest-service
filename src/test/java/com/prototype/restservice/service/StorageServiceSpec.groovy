package com.prototype.restservice.service

import com.prototype.restservice.configuration.StorageProperties
import com.prototype.restservice.exceptions.StorageException
import com.prototype.restservice.service.impl.StorageServiceImpl
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

class StorageServiceSpec extends Specification {

    StorageService storageService
    StorageProperties properties = new StorageProperties()

    def setup() {
        File tempDir = File.createTempDir()
        properties.setLocation(tempDir.getPath())
        storageService = new StorageServiceImpl(properties)
    }

    def "Test store"() {
        given:
        MockMultipartFile multipartFile = new MockMultipartFile(
                "test",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "test".getBytes()
        )

        when:
        storageService.store(multipartFile)

        then:
        0 * _

        and:
        noExceptionThrown()
    }

    def "Test store - StorageException - empty file"() {
        given:
        MockMultipartFile multipartFile = new MockMultipartFile(
                "test",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "".getBytes()
        )

        when:
        storageService.store(multipartFile)

        then:
        0 * _

        and:
        thrown(StorageException)
    }

    def "Test store - StorageException - contains .."() {
        given:
        MockMultipartFile multipartFile = new MockMultipartFile(
                "test",
                "..test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "test".getBytes()
        )

        when:
        storageService.store(multipartFile)

        then:
        0 * _

        and:
        thrown(StorageException)
    }
}
