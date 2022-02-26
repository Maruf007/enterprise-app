package com.resiliencecc.web.security;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class CurrentUser implements Serializable {

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private LocalDateTime loggedinAt;

    @PostConstruct
    protected void init() {
        loggedinAt = LocalDateTime.now();
    }

}
