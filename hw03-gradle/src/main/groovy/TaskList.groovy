import java.time.LocalDate
import java.time.LocalTime

interface TaskList {

    void addTask(Task task)
    void deleteTask(Task task)
    void addAction(Task task, Action action)
    void deleteAction(Task task, Action action)
    int countTasks(LocalDate date)

    LocalTime busyTimeCount(LocalDate date)
    List<Task> getTaskList(LocalDate date)
}