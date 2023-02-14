package hexlet.code.common;

public enum DiffStatuses {
    ADDED("+"),
    REMOVED("-"),
    SAME(" ");

    public final String value;

    DiffStatuses(String value) {
        this.value = value;
    }
}
