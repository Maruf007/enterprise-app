package com.resiliencecc.api.model;

import com.resiliencecc.api.constraint.BookSessionPolicyType;
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
@Table(name = "booksessionpolicy")
@SuppressWarnings("PersistenceUnitPresent")
public class BookSessionPolicy extends PolicyEntity<BookSessionPolicyType> {

}
