package ru.job4j.rest.client;

import ru.job4j.rest.client.actions.*;
import ru.job4j.rest.client.io.ConsoleInput;
import ru.job4j.rest.client.io.ConsoleOutput;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;

import java.util.Map;

public class ConsoleClient {

    public static final String API = "http://localhost:8080/person";

    public static final String API_ID = "http://localhost:8080/person/{id}";

    private static final Output OUT = new ConsoleOutput();

    private static final Input IN = new ConsoleInput(OUT);

    private final Map<Integer, UserAction> actions = Map.of(
            1, new GetAllAction(),
            2, new GetByIdAction(),
            3, new PostAction(),
            4, new PutAction(),
            5, new DeleteAction(),
            6, new ExitAction()
    );

    public void run() {
        boolean exit;
        do {
            showMenu();
            int act = Integer.parseInt(IN.readLine("Enter action: "));
            UserAction userAction = actions.get(act);
            exit = !userAction.execute(IN, OUT);
        } while (!exit);
    }

    private void showMenu() {
        actions.forEach((key, value) -> System.out.println(key + ". " + value.name()));
    }

    public static void main(String[] args) {
        new ConsoleClient().run();
    }

}
