import java.time.LocalDateTime

class Event {
    private def id
    private def name
    private Action action

    Event(id, name, action) {
        this.id = id
        this.name = name
        this.action = action
    }

    def getId() {
        return id
    }

    void setId(id) {
        this.id = id
    }

    def getName() {
        return name
    }

    void setName(name) {
        this.name = name
    }


    LocalDateTime getStartEvent() {
        return startEvent
    }

    void setStartEvent(LocalDateTime startEvent) {
        this.startEvent = startEvent
    }

    LocalDateTime getEndEvent() {
        return endEvent
    }

    void setEndEvent(LocalDateTime endEvent) {
        this.endEvent = endEvent
    }

    def printMessageEvent() {
        if (LocalDateTime.now().getSecond() == action.getStartAction().getSecond()) {
            println("Event: ${name}. Start action: ${action.toString()}\r\nto: ${action.getEndAction()}")
            return "Event: ${name}. Start action: ${action.toString()}\r\nto: ${action.getEndAction()}"
        }
    }

    @Override
    String toString() {
        return "Event{" +
                "id=" + id +
                ", name=" + name +
                ", action=" + action +
                '}';
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Event event = (Event) o

        if (action != event.action) return false
        if (id != event.id) return false
        if (name != event.name) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + action.hashCode()
        return result
    }
}
