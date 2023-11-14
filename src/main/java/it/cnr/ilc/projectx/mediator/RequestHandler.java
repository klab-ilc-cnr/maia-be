package it.cnr.ilc.projectx.mediator;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.projectx.xresults.XResult;

/**
 * @param <TRequest> The type of request that will be handled
 * @param <TResponse> The type of response that will be produced when handling
 * the request.
 */
public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {

    /**
     * @param request The request instance that was passed through mediator
     * object.
     * @return The result of handling the given request.
     */
    TResponse handle(final TRequest request);

    XResult<TResponse> handleXResult(final TRequest request) throws JsonProcessingException;
}
