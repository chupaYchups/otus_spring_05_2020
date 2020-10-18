package ru.chupaYchups.question.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "testing")
public class TestingProps {

    private String fileName;
    private int successQty;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getSuccessQty() {
        return successQty;
    }
    public void setSuccessQty(int successQty) {
        this.successQty = successQty;
    }
}
