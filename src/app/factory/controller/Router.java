package app.factory.controller;

import app.type.Types;

import java.util.Optional;

public interface Router {
    Optional<Strategies> getStrategy(String data);
}
