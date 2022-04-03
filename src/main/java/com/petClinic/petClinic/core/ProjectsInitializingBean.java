package com.petClinic.petClinic.core;

import org.springframework.beans.factory.InitializingBean;

public class ProjectsInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ProjectsInitializingBean in");
    }
}
