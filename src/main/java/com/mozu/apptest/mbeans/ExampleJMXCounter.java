package com.mozu.apptest.mbeans;

public class ExampleJMXCounter implements ExampleJMXCounterMBean {

    private long exampleCount = 0;
    
    /**
     * JMX accessible attribute
     */
    @Override
    public long getExampleCount() {
        return exampleCount;
    }

    /**
     * JM accessible operation
     */
    @Override
    public void clearExampleCount() {
        exampleCount = 0;
    }
    
    /**
     * Non-jmx accessible operation for application usage
     */
    public void incrementExampleCount() {
        exampleCount++;
    }
}
