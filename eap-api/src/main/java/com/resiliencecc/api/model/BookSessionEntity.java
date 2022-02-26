package com.resiliencecc.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@EqualsAndHashCode(of = "sessionId")
@Entity
@Table(name = "booksessionentity")
@SuppressWarnings("PersistenceUnitPresent")
public class BookSessionEntity implements Serializable {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "sessionid", nullable = false)
    private Long sessionId;

    @NotBlank
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @NotNull
    @FutureOrPresent
    @Basic(optional = false)
    @Column(name = "sessiondate", nullable = false, columnDefinition = "date")
    private LocalDate sessionDate;

    @NotNull
    @Basic(optional = false)
    @Column(name = "fromtime", nullable = false, columnDefinition = "time")
    private LocalTime fromTime;

    @NotNull
    @Basic(optional = false)
    @Column(name = "totime", nullable = false, columnDefinition = "time")
    private LocalTime toTime;

    @NotNull
    @Basic(optional = false)
    @Column(name = "clinicid", nullable = false)
    private Integer clinicId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "locationid", nullable = false)
    private Integer locationId;

    @NotEmpty
    @AuditJoinTable(name = "booksessionservice_audit")
    @ElementCollection
    @CollectionTable(name = "booksessionservice", joinColumns = @JoinColumn(name = "sessionid"))
    @Column(name = "serviceid", nullable = false)
    private Set<Integer> serviceIds;

    @NotNull
    @Basic(optional = false)
    @Column(name = "statusid", nullable = false)
    private Integer statusId;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
