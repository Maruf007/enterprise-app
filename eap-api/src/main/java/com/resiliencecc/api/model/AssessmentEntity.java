package com.resiliencecc.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@EqualsAndHashCode(of = "assessmentId")
@Entity
@Table(name = "assessmententity")
@SuppressWarnings("PersistenceUnitPresent")
public class AssessmentEntity implements Serializable {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "assessmentid", nullable = false)
    private Long assessmentId;

    @NotBlank
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "assessmentdate", nullable = false, columnDefinition = "date")
    private LocalDate assessmentDate;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
