package com.example.neo4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");
        /*Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String prompt = "fog> ";
        while (true) {
            String line;
            try {
                line = lineReader.readLine(prompt);
                System.out.println(line);
            } catch (UserInterruptException e) { // Do nothing } catch (EndOfFileException e) {
                System.out.println("\nBye.");
                return;
            }
        }*/
    }
}

