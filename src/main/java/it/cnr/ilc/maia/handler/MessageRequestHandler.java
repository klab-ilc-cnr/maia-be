package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.MessageRequest;
import it.cnr.ilc.maia.xresults.XResult;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestHandler implements RequestHandler<MessageRequest, String> {

    @Override
    public String handle(MessageRequest request) {
        // Demos that we now have access and can use the request instance that matched with this handler.
        return request.getMessage();
    }

    @Override
    public XResult<String> handleXResult(MessageRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
