package it.cnr.ilc.projectx.handler;

import it.cnr.ilc.projectx.mediator.RequestHandler;
import it.cnr.ilc.projectx.request.TestRequest;
import org.springframework.stereotype.Component;

@Component
public class TestRequestHandler implements RequestHandler<TestRequest, String> {
    @Override
    public String handle(TestRequest request) {
        // Just returns a hard coded value.
        return "Test Request";
    }
}
