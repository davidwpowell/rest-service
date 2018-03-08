package com.prototype.restservice.controller

import com.prototype.restservice.service.StorageService
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap
import spock.lang.Specification

class FileUploadControllerSpec extends Specification {

    StorageService storageService = Mock(StorageService)
    FileUploadController fileUploadController = new FileUploadController(
            storageService
    )

    def "Test handleFileUpload"() {
        given:
        MockMultipartFile multipartFile = new MockMultipartFile(
                "test",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "test".getBytes()
        )
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap()

        when:
        String view = fileUploadController.handleFileUpload(multipartFile, redirectAttributes)

        then:
        1 * storageService.store(multipartFile)
        0 * _

        and:
        view == "redirect:/upload"
    }
}
