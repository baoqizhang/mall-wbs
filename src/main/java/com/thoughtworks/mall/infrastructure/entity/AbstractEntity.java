package com.thoughtworks.mall.infrastructure.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant createdAt;

    private Instant updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void updateBasicInfo(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
