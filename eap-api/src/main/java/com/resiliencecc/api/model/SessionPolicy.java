package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.SessionPolicyType;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@Data
@Audited
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "policyId")
@Entity
@Table(name = "sessionpolicy")
@SuppressWarnings("PersistenceUnitPresent")
public class SessionPolicy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "policy_id")
    @TableGenerator(name = "policy_id", table = "sequencestore",
            pkColumnName = "tablename", pkColumnValue = "policyentity", valueColumnName = "keyvalue",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "policyid", nullable = false)
    private Integer policyId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "policytype", nullable = false, updatable = false)
    private SessionPolicyType policyType;

    @Lob
    @NotBlank
    @Basic(optional = false)
    @Column(name = "policyname", nullable = false, columnDefinition = "text")
    private String policyName;

    @NotNull
    @Positive
    @Basic(optional = false)
    @Column(name = "policyorder", nullable = false)
    private Integer policyOrder;

    @NotNull
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Basic(optional = true)
    @Column(name = "parentid", nullable = true)
    private Integer parentId;

    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
