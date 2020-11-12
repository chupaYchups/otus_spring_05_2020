package ru.chupaYchups;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class ApplicationMain {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ApplicationMain.class, args);
		Console.main(args);
	}
}
