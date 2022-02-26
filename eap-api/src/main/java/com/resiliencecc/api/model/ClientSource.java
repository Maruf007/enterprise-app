package com.resiliencecc.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Audited
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "clientId")
@Entity
@Table(name = "clientsource")
@SuppressWarnings("PersistenceUnitPresent")
public class ClientSource implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @Basic(optional = true)
    @Column(name = "sourceid", nullable = true)
    private Integer sourceId;

    @Basic(optional = true)
    @Column(name = "referrername", nullable = true, length = 100)
    private String referrerName;

    @Basic(optional = true)
    @Column(name = "referraldate", nullable = true, columnDefinition = "date")
    private LocalDate referralDate;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
