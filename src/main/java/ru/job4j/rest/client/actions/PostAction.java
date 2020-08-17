package ru.job4j.rest.client.actions;

import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.client.ConsoleClient;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;
import ru.job4j.rest.model.Person;

public class PostAction implements UserAction {
    @Override
    public String name() {
        return "POST";
    }

    @Override
    public boolean execute(Input input, Output output) {
        String login = input.readLine("Enter login: ");
        String password = input.readLine("Enter password: ");
        Person person = new Person(login, password);
        RestTemplate rest = new RestTemplate();
        Person result = rest.postForObject(ConsoleClient.API, person, Person.class);
        output.write(result + System.lineSeparator());
        return true;
    }
}
