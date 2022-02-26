package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.PriceType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
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
@EqualsAndHashCode(of = "policyId")
@Entity
@Table(name = "invoicepolicy")
@SuppressWarnings("PersistenceUnitPresent")
public class InvoicePolicy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "policy_id")
    @TableGenerator(name = "policy_id", table = "sequencestore",
            pkColumnName = "tablename", pkColumnValue = "policyentity", valueColumnName = "keyvalue",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "policyid", nullable = false)
    private Integer policyId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "clienttypeid", nullable = false)
    private Integer clientTypeId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "sessionstatusid", nullable = false)
    private Integer sessionStatusId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "pricetype", nullable = false)
    private PriceType priceType;

    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @Basic(optional = false)
    @Column(name = "amountorpercentage", nullable = false, precision = 8, scale = 2)
    private BigDecimal amountOrPercentage;

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
