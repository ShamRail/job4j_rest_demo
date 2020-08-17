package ru.job4j.rest.client.actions;

import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.client.ConsoleClient;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;
import ru.job4j.rest.model.Person;

public class PutAction implements UserAction {
    @Override
    public String name() {
        return "PUT";
    }

    @Override
    public boolean execute(Input input, Output output) {
        int id = Integer.parseInt(input.readLine("Enter id: "));
        String login = input.readLine("Enter login: ");
        String password = input.readLine("Enter password: ");
        Person person = new Person(id, login, password);
        RestTemplate rest = new RestTemplate();
        rest.put(ConsoleClient.API, person);
        return true;
    }
}
