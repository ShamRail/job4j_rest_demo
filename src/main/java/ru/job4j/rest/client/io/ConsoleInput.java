package ru.job4j.rest.client.io;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    private final Output output;

    public ConsoleInput(Output output) {
        this.output = output;
    }

    @Override
    public String readLine(String msg) {
        output.write(msg);
        return scanner.nextLine();
    }
}
