package org.bolivianjug.crud.control;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by julio.rocha on 27/6/20.
 *
 * @author julio.rocha
 */
public class NumbersApiFallback implements FallbackHandler<String> {
    static final Logger LOG = LoggerFactory.getLogger(NumbersApiFallback.class);

    @Override
    public String handle(ExecutionContext ec) {
        LOG.error("Operation failed in method {} ", ec.getMethod().getName(), ec.getFailure());
        return ec.getParameters()[0] + " is a good Number";
    }
}
