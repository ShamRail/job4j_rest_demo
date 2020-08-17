package ru.job4j.rest.client.actions;

import ru.job4j.rest.client.io.Input;
import ru.job4j.rest.client.io.Output;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "EXIT";
    }

    @Override
    public boolean execute(Input input, Output output) {
        return false;
    }
}
