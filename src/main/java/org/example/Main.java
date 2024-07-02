package org.example;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static int EXECUTION_ROUNDS =  1000;
    public static final ThreadLocal<DummyRequestContext> TL_REQUEST_DATA = ThreadLocal.withInitial(DummyRequestContext::new);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(1,EXECUTION_ROUNDS)
            .parallel()
            .mapToObj(i-> UUID.randomUUID().toString())
            .forEach(requestId -> executorService.execute(()-> {
                TL_REQUEST_DATA.get().setRequestId(requestId);
                DummyWorkflowSimulation.handleRequest();}
                ));
        executorService.shutdown();
    }
}