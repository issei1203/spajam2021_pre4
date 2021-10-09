package com.spajam2021_pre4.spajam2021_pre4.Repository;

import com.spajam2021_pre4.spajam2021_pre4.Repository.Entity.LogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpjRepository extends JpaRepository<LogsEntity,Integer> {

    long countByUserId(String uid);
}
