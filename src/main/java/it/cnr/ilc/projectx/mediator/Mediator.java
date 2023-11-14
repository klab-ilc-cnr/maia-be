package it.cnr.ilc.projectx.mediator;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.cnr.ilc.projectx.xresults.XResult;

/**
 *
 */
public interface Mediator {

    /**
     * @param request The request that will be handled by its registered
     * handler, if it exists.
     * @param <T> The type of the response that will be returned.
     * @return The result of the executed handler.
     */
    <T> T send(final Request<T> request);

    <T> XResult<T> sendXResult(final Request<T> request) throws JsonProcessingException;
}
