package com.resiliencecc.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@EqualsAndHashCode(of = "policyId")
@Entity
@Table(name = "servicechargepolicy")
@SuppressWarnings("PersistenceUnitPresent")
public class ServiceChargePolicy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "policy_id")
    @TableGenerator(name = "policy_id", table = "sequencestore",
            pkColumnName = "tablename", pkColumnValue = "policyentity", valueColumnName = "keyvalue",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "policyid", nullable = false)
    private Integer policyId;

    @NotBlank
    @Size(min = 3, max = 3)
    @Basic(optional = false)
    @Column(name = "servicecode", nullable = false, unique = true, length = 3)
    private String serviceCode;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "servicename", nullable = false, length = 100)
    private String serviceName;

    @Basic(optional = true)
    @Column(name = "description", nullable = true, length = 200)
    private String description;

    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @Basic(optional = false)
    @Column(name = "chargeamount", nullable = false, precision = 8, scale = 2)
    private BigDecimal chargeAmount;

    @NotNull
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
