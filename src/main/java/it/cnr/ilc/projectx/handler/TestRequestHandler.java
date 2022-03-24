package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.TestRequest;
import it.cnr.ilc.projectx.xresults.XResult;
import org.jboss.resteasy.spi.NotImplementedYetException;
import org.springframework.stereotype.Component;

@Component
public class TestRequestHandler implements RequestHandler<TestRequest, String> {
    @Override
    public String handle(TestRequest request) {
        // Just returns a hard coded value.
        return "Test Request";
    }

    @Override
    public XResult<String> handleXResult(TestRequest request) {
        throw new NotImplementedYetException();
    }
}
