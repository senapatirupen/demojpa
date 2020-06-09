package com.exspring.demo.entity.companyentity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class AuditLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "CREATED_BY", nullable = false, unique = false)
    protected String createdBy;
    @Column(name = "CREATED_DATE", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;
    @Column(name = "LAST_MODIFIED_BY", nullable = false, unique = false)
    protected String lastModifiedBy;
    @Column(name = "LAST_MODIFIED_DATE", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;

    @PrePersist
    public void onPrePersist() {
        setCreatedBy("Rupen");
        setCreatedDate(new Date());
        setLastModifiedBy("Rupen");
        setLastModifiedDate(new Date());
    }

    @PreRemove
    @PreUpdate
    public void onPreUpdate() {
        setLastModifiedDate(new Date());
    }
}
