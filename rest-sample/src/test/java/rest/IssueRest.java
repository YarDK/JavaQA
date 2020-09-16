package rest;

import java.util.Objects;

public class IssueRest {

    private int id;
    private String subject;
    private String description;

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public IssueRest withId(int id) {
        this.id = id;
        return this;
    }

    public IssueRest withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public IssueRest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueRest issueRest = (IssueRest) o;
        return id == issueRest.id &&
                Objects.equals(subject, issueRest.subject) &&
                Objects.equals(description, issueRest.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }
}
