package com.prototype.restservice.repository;

import com.prototype.restservice.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
