package com.resiliencecc.core.audit;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Data
@NoArgsConstructor
@Entity
@RevisionEntity(AuditListener.class)
@Table(name = "auditentity")
@SuppressWarnings("PersistenceUnitPresent")
public class AuditEntity implements Serializable {

    private static final long serialVersionUID = 2869121312287390869L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Basic(optional = false)
    @Column(name = "auditid", nullable = false, updatable = false)
    private Long auditId;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "auditat", nullable = false, updatable = false)
    private Date auditAt;

    @Basic(optional = false)
    @Column(name = "userid", nullable = false, updatable = false, length = 6)
    private String userId;

}
