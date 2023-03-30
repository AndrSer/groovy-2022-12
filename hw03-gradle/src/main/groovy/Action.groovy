import java.time.LocalDateTime

class Action {
    private def name
    private def description
    private LocalDateTime startAction
    private LocalDateTime endAction

    Action() {}

    Action(name, description, LocalDateTime startAction, LocalDateTime endAction) {
        this.name = name
        this.description = description
        this.startAction = startAction
        this.endAction = endAction
    }

    def getName() {
        return name
    }

    void setName(name) {
        this.name = name
    }

    def getDescription() {
        return description
    }

    void setDescription(description) {
        this.description = description
    }

    LocalDateTime getStartAction() {
        return startAction
    }

    void setStartAction(LocalDateTime startAction) {
        this.startAction = startAction
    }

    LocalDateTime getEndAction() {
        return endAction
    }

    void setEndAction(LocalDateTime endAction) {
        this.endAction = endAction
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Action action = (Action) o

        if (description != action.description) return false
        if (endAction != action.endAction) return false
        if (name != action.name) return false
        if (startAction != action.startAction) return false

        return true
    }

    int hashCode() {
        int result
        result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + startAction.hashCode()
        result = 31 * result + endAction.hashCode()
        return result
    }

    @Override
    String toString() {
        return "Action{" +
                ", name=" + name +
                ", description=" + description +
                ", startAction=" + startAction +
                ", endAction=" + endAction +
                '}'
    }
}
