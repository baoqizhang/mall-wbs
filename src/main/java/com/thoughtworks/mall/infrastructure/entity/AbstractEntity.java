package com.thoughtworks.mall.infrastructure.entity;

import lombok.Getter;

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

   private Instant createdAt;

   private Instant updatedAt;

   public void setId(Long id) {
      this.id = id;
   }

   public void updateBasicInfo(Instant createdAt, Instant updatedAt) {
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
   }
}
