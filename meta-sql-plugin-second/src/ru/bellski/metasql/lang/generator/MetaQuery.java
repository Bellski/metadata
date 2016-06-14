package ru.bellski.metasql.lang.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by oem on 6/14/16.
 */
public class MetaQuery {
    private final String name;
    private String query;
    private List<SetParameterStep> steps;

    public MetaQuery(String name, List<String> paramNames) {
        this.name = name.concat("Query");

        steps = new ArrayList<>();

        if (!paramNames.isEmpty()) {
            steps.add(new SetParameterStep(this.name.concat("Executor")));
        } else {
            paramNames.forEach(paramName -> steps.add(new SetParameterStep(paramName)));
        }
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SetParameterStep> getSteps() {
        return steps;
    }

    public SetParameterStep getFirstStep() {
        return steps.get(0);
    }
}
