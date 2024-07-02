package org.example;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DummyWorkflowSimulation {
    static Logger logger = Logger.getLogger(DummyWorkflowSimulation.class.getName());
    final static ThreadLocal<DummyRequestContext> TL_SESSION_DATA = ThreadLocal.withInitial(DummyRequestContext::new);
    public static void handleRequest(String requestId) {

        DummyRequestContext requestContext = TL_SESSION_DATA.get();
        requestContext.setRequestId(requestId);
        if(requestContext.getToken() == null) {
            requestContext.setToken(requestId + UUID.randomUUID());
        }
        validateToken(requestContext);
        TL_SESSION_DATA.set(requestContext);
    }

    private static void validateToken(DummyRequestContext requestContext){
        if(!requestContext.getToken().startsWith(requestContext.getRequestId())){
            logger.log(Level.SEVERE,"Token is invalid for context: "+requestContext.getRequestId());
            throw new IllegalStateException("Invalid context token.");
        }else{
            logger.log(Level.FINE,"Token is valid for context: "+requestContext.getRequestId());
        }
    }

}
