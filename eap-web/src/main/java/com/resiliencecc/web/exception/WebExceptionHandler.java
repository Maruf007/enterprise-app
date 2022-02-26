package com.resiliencecc.web.exception;

import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.exception.DataNotFoundException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.Priority;
import javax.faces.application.FacesMessage;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Interceptor
@WebException
@Priority(value = Interceptor.Priority.APPLICATION)
public class WebExceptionHandler implements Serializable {

    private static final long serialVersionUID = 1151729977799191548L;
    private static final ResourceBundle messageBundle = Faces.getResourceBundle("msg");

    @AroundInvoke
    public Object exceptionCatch(InvocationContext invocationContext) {
        try {
            return invocationContext.proceed();
        } catch (BusinessException | DataNotFoundException ex) {
            String summary = messageBundle.getString("header");
            Messages.addGlobal(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ex.getMessage()));
        } catch (Exception ex) {
            String detail = ex.getMessage() != null ? ex.getMessage() : "Internal server error.";
            Messages.addGlobal(new FacesMessage(FacesMessage.SEVERITY_ERROR, "500", detail));
        }

        return null;
    }

}
