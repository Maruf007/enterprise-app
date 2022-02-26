package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.InvoiceStatus;
import com.resiliencecc.api.constraint.PaymentMethod;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
@EqualsAndHashCode(of = "invoiceId")
@Entity
@Table(name = "invoiceentity")
@SuppressWarnings("PersistenceUnitPresent")
public class InvoiceEntity implements Serializable {

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "invoiceid", nullable = false)
    private Long invoiceId;

    @Basic(optional = false)
    @Column(name = "invoicereference", nullable = false, unique = true, length = 18)
    private String invoiceReference;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "invoicestatus", nullable = false)
    private InvoiceStatus invoiceStatus;

    @NotNull
    @PastOrPresent
    @Basic(optional = false)
    @Column(name = "invoicedate", nullable = false, columnDefinition = "date")
    private LocalDate invoiceDate;

    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @Basic(optional = false)
    @Column(name = "payableamount", nullable = false, precision = 8, scale = 2)
    private BigDecimal payableAmount;

    @Enumerated(EnumType.STRING)
    @Basic(optional = true)
    @Column(name = "paymentmethod", nullable = true)
    private PaymentMethod paymentMethod;

    @Basic(optional = true)
    @Column(name = "paymentaccount", nullable = true, length = 100)
    private String paymentAccount;

    @Basic(optional = true)
    @Column(name = "paymentamount", nullable = true, precision = 8, scale = 2)
    private BigDecimal paymentAmount;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
