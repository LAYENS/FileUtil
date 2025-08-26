package app.factory.controller;

import app.type.Types;

import java.nio.file.Path;
import java.util.Optional;

public interface Strategies {
    Optional<Types> getType(String Line);
    void write(String data, Path outPath, String prefix, boolean isRewrite);
}
