package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.Role;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Audited
@DynamicUpdate
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "cellPhone"})
@Entity
@Table(name = "userentity")
@SuppressWarnings("PersistenceUnitPresent")
public class UserEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "userid", nullable = false, unique = true, length = 6)
    private String userId;

    @NotAudited
    @NotBlank
    @Size(min = 1, max = 200)
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @NotBlank
    @Size(min = 1, max = 20)
    @Basic(optional = false)
    @Column(name = "cellphone", nullable = false, unique = true, length = 20)
    private String cellPhone;

    @Basic(optional = true)
    @Column(name = "emailaddress", nullable = true, unique = true, length = 100)
    private String emailAddress;

    @NotNull
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @AuditJoinTable(name = "userrole_audit")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userrole", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "rolename", nullable = false)
    private Set<Role> roles;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
