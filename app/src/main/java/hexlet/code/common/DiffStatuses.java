package hexlet.code.common;

public enum DiffStatuses {
    ADDED(" "),
    REMOVED(" "),
    UPDATED(" "),
    SAME(" ");

    public final String value;

    DiffStatuses(String value) {
        this.value = value;
    }
}
