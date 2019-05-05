package com.prototype.restservice.controller;

import com.prototype.restservice.model.SampleDto;
import com.prototype.restservice.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping(value = "/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("ALIVE");
    }

    @GetMapping(value = "/samples/{id}")
    public ResponseEntity<SampleDto> getSample(@PathVariable String id) {
        return ResponseEntity.ok(sampleService.getSample(Long.valueOf(id)));
    }

    @GetMapping(value = "/samples")
    public ResponseEntity<List<SampleDto>> getAllSamples() {
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    @PostMapping(value = "/samples")
    public ResponseEntity<String> createSample(@RequestBody SampleDto sampleDto) {
        Long id = sampleService.addSample(sampleDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/samples/{id}")
    public ResponseEntity<String> updateSample(@PathVariable String id, @RequestBody SampleDto sampleDto) {
        sampleService.updateSample(Long.valueOf(id), sampleDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/samples/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable String id) {
        sampleService.deleteSample(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
