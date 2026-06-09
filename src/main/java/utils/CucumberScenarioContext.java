package utils;

import io.cucumber.java.Scenario;

public class CucumberScenarioContext {
    private static final ThreadLocal<Scenario> scenario =
            new ThreadLocal<>();

    public static void setScenario(Scenario sc) {
        scenario.set(sc);
    }

    public static Scenario getScenario() {
        return scenario.get();
    }

    public static void removeScenario() {
        scenario.remove();
    }
}

