package org.bolivianjug.crud.control;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

/**
 * Created by julio.rocha on 27/6/20.
 *
 * @author julio.rocha
 */
@RegisterRestClient(configKey = "numbers-api")
public interface NumbersApi {
    @GET
    @Path("/{myNumber}/{type}")
    @Retry(maxRetries = 2, delay = 500, retryOn = {ProcessingException.class}, abortOn = {WebApplicationException.class})
    @Fallback(NumbersApiFallback.class)
    String getNumberMessage(@PathParam("myNumber") Integer number, @PathParam("type") String type);
}
