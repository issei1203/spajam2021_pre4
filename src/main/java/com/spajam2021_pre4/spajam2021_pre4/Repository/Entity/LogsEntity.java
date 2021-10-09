package com.spajam2021_pre4.spajam2021_pre4.Repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
public class LogsEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "start")
    private String startTime;
    @Column(name = "user")
    private String userId;
    @Column(name = "current")
    private String currentTime;
}
