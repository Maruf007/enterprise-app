package com.resiliencecc.api.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Audited
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "clientId")
@Entity
@Table(name = "clientstatus")
@SuppressWarnings("PersistenceUnitPresent")
public class ClientStatus implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "typeid", nullable = false)
    private Integer typeId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "statusid", nullable = false)
    private Integer statusId;

    @AuditJoinTable(name = "clientprogress_audit")
    @ElementCollection
    @CollectionTable(name = "clientprogress", joinColumns = @JoinColumn(name = "clientid"))
    @Column(name = "progressid", nullable = false)
    private Set<Integer> progressIds;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
