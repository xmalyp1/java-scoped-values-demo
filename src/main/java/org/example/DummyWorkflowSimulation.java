package org.example;

import static org.example.Main.TL_REQUEST_DATA;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DummyWorkflowSimulation {
    static Logger logger = Logger.getLogger(DummyWorkflowSimulation.class.getName());
    public static void handleRequest() {
        DummyRequestContext requestContext = TL_REQUEST_DATA.get();
        if(requestContext.getToken() == null){
            requestContext.setToken(generateToken(requestContext.getRequestId()));
        }
        validateToken(requestContext);
        logger.log(Level.INFO,String.format("Handling request: %s",requestContext.getRequestId()));
    }

    private static void validateToken(DummyRequestContext requestContext){
        if(!requestContext.getToken().startsWith(requestContext.getRequestId())){
            logger.log(Level.SEVERE,"Token is invalid for context: "+requestContext.getRequestId());
            throw new IllegalStateException("Invalid context token.");
        }else{
            logger.log(Level.FINE,"Token is valid for context: "+requestContext.getRequestId());
        }
    }

    private static String generateToken(String requestId){
        return requestId + UUID.randomUUID();
    }

}
