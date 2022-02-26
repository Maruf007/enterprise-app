package com.resiliencecc.web.controller;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import lombok.SneakyThrows;
import org.omnifaces.util.Faces;

@Model
public class LogoutController {

    @SneakyThrows(ServletException.class)
    public void logout() {
        Faces.logout();
        Faces.invalidateSession();
        Faces.refresh();
    }

}
