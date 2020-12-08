package ru.chupaYchups.question.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.chupaYchups.question.service.TestingAttemptService;

@ShellComponent
public class ShellCommands {

    private final TestingAttemptService testingAttemptService;
    private String userName;

    @Autowired
    public ShellCommands(TestingAttemptService testingAttemptService) {
        this.testingAttemptService = testingAttemptService;
    }

    @ShellMethod(value = "hello", key = {"hello", "h"})
    public String hello(@ShellOption String userName) {
        this.userName = userName;
        return "user with name " + userName + " login sucessfully";
    }


    @ShellMethod(value = "startTesting", key = {"start", "st", "s"})
    @ShellMethodAvailability(value="isStartCommandAvailable")
    public String startTesting() {
        testingAttemptService.doTestingAttempt();
        return null;
    }

    private Availability isStartCommandAvailable() {
        return userName == null ? Availability.unavailable("user is not logged in, please do 'hello' command previously") : Availability.available();
    }
}
