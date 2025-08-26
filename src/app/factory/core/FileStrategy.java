package app.factory.core;

import app.factory.controller.Strategies;
import app.factory.controller.Router;
import app.factory.strategy.DoubleStrategy;
import app.factory.strategy.IntegerStrategy;
import app.factory.strategy.StringStrategy;

import java.util.List;
import java.util.Optional;

public class FileStrategy implements Router {
    private final List<Strategies> strategies = List.of(
      new IntegerStrategy(),
      new DoubleStrategy(),
      new StringStrategy()
    );
    @Override
    public Optional<Strategies> getStrategy(String data) {
        return strategies.stream().
                filter(strategy-> strategy.getType(data).isPresent())
                .findFirst();
    }
}
