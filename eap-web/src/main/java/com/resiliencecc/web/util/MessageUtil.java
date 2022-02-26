package com.resiliencecc.web.util;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import lombok.SneakyThrows;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

public final class MessageUtil {

    private static final ResourceBundle messageBundle = Faces.getResourceBundle("msg");

    @SneakyThrows(ClassNotFoundException.class)
    public static void success(Object param) {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[2];
        Class clazz = Class.forName(traceElement.getClassName());
        Method[] methods = clazz.getMethods();
        String key = Save.class.getSimpleName().toLowerCase();

        for (Method method : methods) {
            if (method.getName().equals(traceElement.getMethodName())) {
                if (method.isAnnotationPresent(Save.class)) {
                    key = Save.class.getSimpleName().toLowerCase();
                } else if (method.isAnnotationPresent(Update.class)) {
                    key = Update.class.getSimpleName().toLowerCase();
                } else if (method.isAnnotationPresent(Delete.class)) {
                    key = Delete.class.getSimpleName().toLowerCase();
                }
            }
        }

        String summary = messageBundle.getString("header");
        String detail = messageBundle.getString(key);
        MessageFormat format = new MessageFormat(detail);
        detail = format.format(new String[]{param.toString()});

        Messages.addGlobal(new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

}
