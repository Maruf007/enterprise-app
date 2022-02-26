package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.ClientPolicyType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

@Data
@Audited
@AuditOverrides({
    @AuditOverride(forClass = PolicyEntity.class, isAudited = true),
    @AuditOverride(forClass = PolicyEntity.class, isAudited = false, name = "versionId")
})
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clientpolicy")
@SuppressWarnings("PersistenceUnitPresent")
public class ClientPolicy extends PolicyEntity<ClientPolicyType> {

    @Basic(optional = true)
    @Column(name = "parentid", nullable = true)
    private Integer parentId;

}
