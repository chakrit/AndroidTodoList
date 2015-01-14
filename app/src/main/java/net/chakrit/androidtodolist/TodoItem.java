package net.chakrit.androidtodolist;

public class TodoItem {
    public String description;
    public boolean isCompleted;

    public void toggle() {
        isCompleted = !isCompleted;
    }

    @Override
    public String toString() {
        return description;
    }
}
