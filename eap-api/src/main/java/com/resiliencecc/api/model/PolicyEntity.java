package com.resiliencecc.api.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "policyId")
@MappedSuperclass
@SuppressWarnings({"serial", "ValidAttributes"})
public abstract class PolicyEntity<PolicyType> implements Serializable {

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
    private PolicyType policyType;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "policyname", nullable = false, length = 100)
    private String policyName;

    @NotNull
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
