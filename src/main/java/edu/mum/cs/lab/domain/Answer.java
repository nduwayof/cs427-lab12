package edu.mum.cs.lab.domain;

import java.util.Objects;

public class Answer {

    private int id;
    private int ans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public Answer() {
    }

    public Answer(int id, int ans) {
        this.id = id;
        this.ans = ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId() == answer.getId() &&
                getAns() == answer.getAns();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAns());
    }
}
