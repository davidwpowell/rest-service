package com.prototype.restservice.controller;

import com.prototype.restservice.model.SampleDto;
import com.prototype.restservice.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AppController {

    private final SampleService sampleService;

    @Autowired
    public AppController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

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

    @RequestMapping(value = "/samples", method = RequestMethod.POST)
    public ResponseEntity<?> createSample(@RequestBody SampleDto sampleDto) {
        Long id = sampleService.addSample(sampleDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}
