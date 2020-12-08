package ru.chupaYchups.question.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "testing")
public class TestingProps {

    private int successQty;

    public int getSuccessQty() {
        return successQty;
    }
    public void setSuccessQty(int successQty) {
        this.successQty = successQty;
    }
}
