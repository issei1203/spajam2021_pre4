package com.spajam2021_pre4.spajam2021_pre4.Repository;

import com.spajam2021_pre4.spajam2021_pre4.Repository.Entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleRepository extends JpaRepository<SampleEntity,Integer> {
//    Optional<Long> getMaxId();
//    Optional<SampleEntity> findByTimeByMAxId(Integer integer);
}
