package statistics.matcher;

import statistics.Player;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        matcher = new All();

    }

    public Matcher build() {
        Matcher finishedMatcher = this.matcher;
        this.matcher = new All();
        return finishedMatcher;
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(new PlaysIn(team), matcher);
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(new HasAtLeast(value, category), matcher);
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(new HasFewerThan(value, category), matcher);
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }

}
