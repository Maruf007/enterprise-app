package com.resiliencecc.web;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.omnifaces.cdi.Startup;

@Startup
public class ApplicationInitializer {

    @PostConstruct
    protected void init() {
        ZoneId zoneId = ZoneId.of(ZoneOffset.UTC.getId());
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        TimeZone.setDefault(timeZone);
    }

}
