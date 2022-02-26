package com.resiliencecc.core.audit;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.inject.Inject;
import org.hibernate.envers.RevisionListener;

public class AuditListener implements RevisionListener {

    @Inject
    private Principal principal;

    @Override
    public void newRevision(Object revisionEntity) {
        AuditEntity auditEntity = (AuditEntity) revisionEntity;

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        Date auditAt = Date.from(now.toInstant());
        auditEntity.setAuditAt(auditAt);

        String userId = principal.getName();
        auditEntity.setUserId(userId);
    }

}
