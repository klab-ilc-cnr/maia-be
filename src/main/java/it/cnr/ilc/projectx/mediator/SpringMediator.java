package it.cnr.ilc.projectx.mediator;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

/**
 * Spring mediator makes use of springs IoC container in order to resolve given
 * requests. Any RequestHandlers that are registered as beans will be able to be
 * found by using the ApplicationContext.
 */
@Component
@RequiredArgsConstructor
public class SpringMediator implements Mediator {

    @NonNull
    private final AbstractApplicationContext context;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T send(Request<T> request) {
        final RequestHandler<Request<T>, T> requestHandler = getRequestHandler(request);
        return requestHandler.handle(request);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> XResult<T> sendXResult(Request<T> request) throws JsonProcessingException {
        final RequestHandler<Request<T>, T> requestHandler = getRequestHandler(request);
        return requestHandler.handleXResult(request);
    }

    private <T> RequestHandler<Request<T>, T> getRequestHandler(Request<T> request) {
        if (request == null) {
            throw new NullPointerException("The given request object cannot be null");
        }

        // Types used to find the exact handler that is required to handle the given request.
        // We are able to search all application beans by type from the Spring ApplicationContext.
        final Class requestType = request.getClass();
        final Class<T> responseType = (Class<T>) ((ParameterizedType) requestType.getGenericInterfaces()[0]).getActualTypeArguments()[0];

        // Retrieve RequestHandler beans based on request and response types.
        final String[] beanNames = context.getBeanNamesForType(ResolvableType.forClassWithGenerics(RequestHandler.class, requestType, responseType));

        // There must be a registered handler.
        if (beanNames.length == 0) {
            throw new IllegalStateException("No handlers seemed to be registered to handle the given request. Make sure the handler is defined and marked as a spring component");
        }

        // There may not be more than one handler.
        if (beanNames.length > 1) {
            throw new IllegalStateException("More than one handlers found. Only one handler per request is allowed.");
        }

        final RequestHandler<Request<T>, T> requestHandler = (RequestHandler<Request<T>, T>) context.getBean(beanNames[0]);
        return requestHandler;
    }

}
