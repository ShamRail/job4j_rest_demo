package ru.job4j.rest.client.actions;

import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.client.ConsoleClient;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;
import ru.job4j.rest.model.Person;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "DELETE";
    }

    @Override
    public boolean execute(Input input, Output output) {
        int id = Integer.parseInt(input.readLine("Enter id: "));
        RestTemplate rest = new RestTemplate();
        rest.delete(ConsoleClient.API_ID, id);
        return true;
    }
}
