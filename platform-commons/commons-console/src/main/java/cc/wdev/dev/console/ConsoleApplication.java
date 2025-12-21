package cc.wdev.dev.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.core.command.annotation.EnableCommand;

@Slf4j
@SpringBootApplication
@EnableCommand(ConsoleApplication.class)
public class ConsoleApplication {

    static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
        log.info("The service to end");
    }

}
