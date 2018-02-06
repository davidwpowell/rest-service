package com.prototype.restservice.controller;

import com.prototype.restservice.model.SampleDto;
import com.prototype.restservice.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("ALIVE");
    }

    @RequestMapping(value = "/samples/{id}", method = RequestMethod.GET)
    public ResponseEntity<SampleDto> getSample(@PathVariable String id) {
        return ResponseEntity.ok(sampleService.getSample(Long.valueOf(id)));
    }

    @RequestMapping(value = "/samples", method = RequestMethod.GET)
    public ResponseEntity<List<SampleDto>> getAllSamples() {
        return ResponseEntity.ok(sampleService.getAllSamples());
    }
}
