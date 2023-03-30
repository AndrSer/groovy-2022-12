import java.time.LocalDateTime

class Task {
    private def name
    private List<Action> actions = []
    private LocalDateTime dateTimeStart
    private LocalDateTime dateTimeEnd

    Task() {}

    Task(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, name) {
        this.dateTimeStart = dateTimeStart
        this.dateTimeEnd = dateTimeEnd
        this.name = name
    }

    def getName() {
        return name
    }

    void setName(name) {
        this.name = name
    }

    List<Action> getActions() {
        return actions
    }

    LocalDateTime getDateTimeStart() {
        return dateTimeStart
    }

    void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart
    }

    LocalDateTime getDateTimeEnd() {
        return dateTimeEnd
    }

    void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd
    }

    @Override
    String toString() {
        return "Task{" +
                "actions=" + actions +
                ", dateTimeStart=" + dateTimeStart +
                ", dateTimeEnd=" + dateTimeEnd +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Task task = (Task) o

        if (actions != task.actions) return false
        if (dateTimeEnd != task.dateTimeEnd) return false
        if (dateTimeStart != task.dateTimeStart) return false

        return true
    }

    int hashCode() {
        int result
        result = actions.hashCode()
        result = 31 * result + dateTimeStart.hashCode()
        result = 31 * result + dateTimeEnd.hashCode()
        return result
    }
}
