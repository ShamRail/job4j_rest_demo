package ru.job4j.rest.client.actions;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.client.ConsoleClient;
import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;
import ru.job4j.rest.model.Person;

import java.util.List;

public class GetAllAction implements UserAction {
    @Override
    public String name() {
        return "GET ALL";
    }

    @Override
    public boolean execute(Input input, Output output) {
        RestTemplate rest = new RestTemplate();
        List<Person> persons = rest.exchange(
                ConsoleClient.API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {}
        ).getBody();
        if (persons != null) {
            persons.forEach(p -> output.write(p + System.lineSeparator()));
        }
        return true;
    }
}
