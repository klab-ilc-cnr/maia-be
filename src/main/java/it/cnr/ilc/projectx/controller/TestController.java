package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.DisplayConsoleMessageRequest;
import it.cnr.ilc.projectx.request.MessageRequest;
import it.cnr.ilc.projectx.request.TestRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @NonNull
    private final Mediator mediator;

    @GetMapping("test")
    public ResponseEntity<String> test() {
        final String response = mediator.send(new TestRequest());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("greet")
    public ResponseEntity<String> message(final @RequestParam("message") String message) {
        mediator.send(new DisplayConsoleMessageRequest(message));
        final String response = mediator.send(new MessageRequest(message));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
