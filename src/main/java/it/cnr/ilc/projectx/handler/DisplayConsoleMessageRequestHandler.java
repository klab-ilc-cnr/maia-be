package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.DisplayConsoleMessageRequest;
import it.cnr.ilc.projectx.xresults.XResult;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DisplayConsoleMessageRequestHandler implements RequestHandler<DisplayConsoleMessageRequest, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayConsoleMessageRequestHandler.class);

    @Override
    public Void handle(DisplayConsoleMessageRequest request) {
        // Demos void handling
        LOGGER.info(request.getMessage());
        return null;
    }

    @Override
    public XResult<Void> handleXResult(DisplayConsoleMessageRequest request) {
        throw new NotImplementedYetException();
    }
}
