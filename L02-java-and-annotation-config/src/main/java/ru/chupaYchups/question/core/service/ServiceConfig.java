package ru.chupaYchups.question.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.io.PrintStream;

public class ServiceConfig {

    @Bean
    public PrintOutputService printOutputService(@Value("#{T(java.lang.System).out}")PrintStream printStream) {
        return new PrintOutputServiceImpl(printStream);
    }
    @Bean
    public InputService inputService(@Value("#{T(java.lang.System).in}") InputStream inputStream) {
        return new InputServiceImpl(inputStream);
    }
}
