package it.cnr.ilc.maia.handler;

import it.cnr.ilc.maia.mediator.RequestHandler;
import it.cnr.ilc.maia.request.TestRequest;
import it.cnr.ilc.maia.xresults.XResult;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
