package com.mozu.apptest.mbeans;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.stereotype.Service;

@Service
public class InitJmx {
    private static final String EXAMPLE_JMX_COUNTER_NAME = "com.mozu.apptest:type=ExampleJMXCounter";
    
    protected MBeanServer mbs = null;

    @PostConstruct
    public void initJmx() {
        mbs =  ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.registerMBean(
                    new ExampleJMXCounter(), 
                    new ObjectName(EXAMPLE_JMX_COUNTER_NAME));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PreDestroy
    public void clearJmx() {
        ObjectName name;
        try {
            name = new ObjectName(EXAMPLE_JMX_COUNTER_NAME);
            mbs.unregisterMBean(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
