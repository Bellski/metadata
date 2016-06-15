package ru.bellski.metasql.lang.generator;

import java.util.List;

/**
 * Created by oem on 6/14/16.
 */
public class MetaQuery {
    private final String name;
    private String query;
    private List<Step> steps;

    public MetaQuery(String name, List<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public  void setQuery(String query) {
        this.query = query;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Step getFirstStep() {
        return steps.get(0);
    }
}
