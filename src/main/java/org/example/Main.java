package org.example;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static int EXECUTION_ROUNDS =  1000;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(1,EXECUTION_ROUNDS)
            .parallel()
            .mapToObj(i-> UUID.randomUUID().toString())
            .forEach(DummyWorkflowSimulation::handleRequest);
        executorService.shutdown();
    }
}