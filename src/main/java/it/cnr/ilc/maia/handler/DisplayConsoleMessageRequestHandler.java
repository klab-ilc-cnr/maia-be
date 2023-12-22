package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.DisplayConsoleMessageRequest;
import it.cnr.ilc.maia.xresults.XResult;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
