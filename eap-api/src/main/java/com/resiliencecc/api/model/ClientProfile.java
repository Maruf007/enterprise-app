package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.Gender;
import com.resiliencecc.api.constraint.MaritalStatus;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Audited
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "clientId")
@Entity
@Table(name = "clientprofile")
@SuppressWarnings("PersistenceUnitPresent")
public class ClientProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "client_id")
    @GenericGenerator(name = "client_id",
            strategy = "com.resiliencecc.core.identity.SequenceStringIdGenerator",
            parameters = {
                @Parameter(name = "table_name", value = "sequencestore"),
                @Parameter(name = "segment_column_name", value = "tablename"),
                @Parameter(name = "segment_value", value = "clientprofile"),
                @Parameter(name = "value_column_name", value = "keyvalue"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1"),
                @Parameter(name = "numberFormat", value = "%012d")
            })
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "fullname", nullable = false, length = 100)
    private String fullName;

    @NotBlank
    @Size(min = 1, max = 20)
    @Basic(optional = false)
    @Column(name = "cellphone", nullable = false, unique = true, length = 20)
    private String cellPhone;

    @Basic(optional = true)
    @Column(name = "emergencyphone", nullable = true, length = 20)
    private String emergencyPhone;

    @Basic(optional = true)
    @Column(name = "emailaddress", nullable = true, unique = true, length = 100)
    private String emailAddress;

    @NotNull
    @PastOrPresent
    @Basic(optional = false)
    @Column(name = "birthdate", nullable = false, columnDefinition = "date")
    private LocalDate birthDate;

    @NotNull
    @PastOrPresent
    @Basic(optional = false)
    @Column(name = "creationdate", nullable = false, columnDefinition = "date")
    private LocalDate creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "maritalstatus", nullable = false)
    private MaritalStatus maritalStatus;

    @Basic(optional = true)
    @Column(name = "employmentid", nullable = true)
    private Integer employmentId;

    @NotBlank
    @Basic(optional = false)
    @Column(name = "serviceproviderid", nullable = false, length = 6)
    private String serviceProviderId;

    @Basic(optional = true)
    @Column(name = "uniqueclientidentifier", nullable = true, length = 100)
    private String uniqueClientIdentifier;

    @Basic(optional = true)
    @Column(name = "note", nullable = true, length = 500)
    private String note;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
