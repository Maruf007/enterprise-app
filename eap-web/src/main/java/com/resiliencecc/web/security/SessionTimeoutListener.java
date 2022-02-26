package com.resiliencecc.web.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.FactoryFinder;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.SneakyThrows;
import org.omnifaces.util.Faces;

public class SessionTimeoutListener implements PhaseListener {

    @Override
    @SneakyThrows(IOException.class)
    public void beforePhase(PhaseEvent event) {
        if (Faces.isAjaxRequest() || Faces.isAjaxRequestWithPartialRendering()) {
            if (Faces.getExternalContext().getUserPrincipal() == null) {
                FacesContext facesContext = event.getFacesContext();
                ExternalContext externalContext = facesContext.getExternalContext();

                ServletRequest request = (ServletRequest) externalContext.getRequest();
                String characterEncoding = request.getCharacterEncoding();

                ServletResponse response = (ServletResponse) externalContext.getResponse();
                PrintWriter writer = response.getWriter();

                String RENDER_KIT_FACTORY = FactoryFinder.RENDER_KIT_FACTORY;
                RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(RENDER_KIT_FACTORY);

                ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
                String renderKitId = viewHandler.calculateRenderKitId(facesContext);
                RenderKit renderKit = factory.getRenderKit(facesContext, renderKitId);
                ResponseWriter responseWriter = renderKit.createResponseWriter(writer, null, characterEncoding);

                response.setCharacterEncoding(characterEncoding);
                facesContext.setResponseWriter(responseWriter);
                externalContext.redirect(externalContext.getRequestContextPath());
            }
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
