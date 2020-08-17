package ru.job4j.rest.client.actions;

import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;

public interface UserAction {
    String name();
    boolean execute(Input input, Output output);
}
