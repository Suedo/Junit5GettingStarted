package com.webms.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Getter @Setter
public abstract class AuditModel implements Serializable {
    @Column(name = "created_at", columnDefinition="TIMESTAMP", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP", nullable = false)
    @LastModifiedDate
    private Date updatedAt;


}