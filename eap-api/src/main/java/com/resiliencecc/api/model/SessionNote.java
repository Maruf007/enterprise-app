package com.resiliencecc.api.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@EqualsAndHashCode(of = "noteId")
@Entity
@Table(name = "sessionnote")
@SuppressWarnings("PersistenceUnitPresent")
public class SessionNote implements Serializable {

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "noteid", nullable = false)
    private Long noteId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "formatid", nullable = false)
    private Integer formatId;

    @AuditJoinTable(name = "sessionstatus_audit")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sessionstatus", joinColumns = @JoinColumn(name = "noteid"))
    private List<SessionStatus> sessionStatuses;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
