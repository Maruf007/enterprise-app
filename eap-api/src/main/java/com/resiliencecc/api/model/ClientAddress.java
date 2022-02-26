package com.resiliencecc.api.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
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
@EqualsAndHashCode(of = "clientId")
@Entity
@Table(name = "clientaddress")
@SuppressWarnings("PersistenceUnitPresent")
public class ClientAddress implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "clientid", nullable = false, length = 12)
    private String clientId;

    @NotBlank
    @Size(min = 1, max = 300)
    @Basic(optional = false)
    @Column(name = "streetaddress1", nullable = false, length = 300)
    private String streetAddress1;

    @Basic(optional = true)
    @Column(name = "streetaddress2", nullable = true, length = 300)
    private String streetAddress2;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "cityname", nullable = false, length = 100)
    private String cityName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "provincename", nullable = false, length = 100)
    private String provinceName;

    @NotBlank
    @Size(min = 1, max = 10)
    @Basic(optional = false)
    @Column(name = "postalcode", nullable = false, length = 10)
    private String postalCode;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
