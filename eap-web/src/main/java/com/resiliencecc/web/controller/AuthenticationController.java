package com.resiliencecc.web.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class AuthenticationController implements Serializable {

    @NotBlank
    @Getter
    @Setter
    private String phoneOrEmail;

    @NotBlank
    @Getter
    @Setter
    private String password;

    @Inject
    private SecurityContext securityContext;

    public void login() {
        HttpServletRequest servletRequest = Faces.getRequest();
        HttpServletResponse servletResponse = Faces.getResponse();

        Credential credential = new UsernamePasswordCredential(phoneOrEmail, new Password(password));
        AuthenticationParameters parameters = AuthenticationParameters.withParams().credential(credential);
        AuthenticationStatus status = securityContext.authenticate(servletRequest, servletResponse, parameters);

        switch (status) {
            case SEND_CONTINUE:
                Faces.responseComplete();
                break;

            case SEND_FAILURE:
                ResourceBundle messageBundle = Faces.getResourceBundle("msg");
                String summary = messageBundle.getString("header");
                String detail = messageBundle.getString("forbidden");
                Messages.addGlobal(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
                break;

            case SUCCESS:
                Faces.redirect(Faces.getRequestBaseURL());
                break;
        }
    }

}
