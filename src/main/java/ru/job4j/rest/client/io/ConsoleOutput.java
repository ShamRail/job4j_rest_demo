package ru.job4j.rest.client.io;

public class ConsoleOutput implements Output {
    @Override
    public void write(String msg) {
        System.out.print(msg);
    }
}
