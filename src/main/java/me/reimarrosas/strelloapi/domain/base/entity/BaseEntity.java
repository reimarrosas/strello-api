package me.reimarrosas.strelloapi.domain.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void prePersist() {
        Long now = System.currentTimeMillis();
        createdAt = new Date(now);
        updatedAt = new Date(now);
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = new Date();
    }
}
