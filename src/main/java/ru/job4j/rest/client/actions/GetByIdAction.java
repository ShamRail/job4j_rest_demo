package ru.job4j.rest.client.actions;

import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.client.ConsoleClient;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;
import ru.job4j.rest.model.Person;

public class GetByIdAction implements UserAction {

    @Override
    public String name() {
        return "GET BY ID";
    }

    @Override
    public boolean execute(Input input, Output output) {
        RestTemplate rest = new RestTemplate();
        int id = Integer.parseInt(input.readLine("Enter id: "));
        Person remote = rest.getForObject(ConsoleClient.API_ID, Person.class, id);
        output.write(remote + System.lineSeparator());
        return true;
    }
}
