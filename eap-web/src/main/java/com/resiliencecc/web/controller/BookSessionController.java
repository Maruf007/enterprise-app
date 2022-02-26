package com.resiliencecc.web.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import lombok.Getter;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@Named
@ViewScoped
public class BookSessionController implements Serializable {

    @Getter
    private ScheduleModel bookScheduleModel;

    @PostConstruct
    protected void init() {
        bookScheduleModel = new DefaultScheduleModel();
    }

}
