import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class TaskListImpl implements TaskList {

    private List<Task> tasks = []

    List<Task> getTasks() {
        return tasks
    }

    @Override
    void addTask(Task task) {
        validatePeriods(tasks, task)
        tasks.add(task)
    }

    @Override
    void deleteTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task)
        } else {
            throw new RuntimeException("Task ${task.toString()} is not present on the list")
        }
    }

    @Override
    void addAction(Task task, Action action) {
        if (tasks.contains(task)) {
            def actions = tasks.get(tasks.indexOf(task)).getActions()
            validatePeriods(actions, action)
            validateTimeBoarders(task, action)
            actions.add(action)
        } else {
            throw new RuntimeException("Task ${task.toString()} is not present on the list")
        }
    }

    @Override
    void deleteAction(Task task, Action action) {
        if (tasks.contains(task)) {
            def actions = tasks.get(tasks.indexOf(task)).getActions()
            actions.remove(action)
        } else {
            throw new RuntimeException("Task ${task.toString()} is not present on the list")
        }
    }

    @Override
    int countTasks(LocalDate date) {
        return getTaskList(date).size()
    }

    @Override
    LocalTime busyTimeCount(LocalDate date) {
        def taskListPeriods = getTaskList(date)
                .collect {periodOfSeconds(it.getDateTimeStart(), it.getDateTimeEnd()) }.sum()
        return LocalTime.of(0,0,0,0)
                .plusSeconds(taskListPeriods)
    }

    @Override
    List<Task> getTaskList(LocalDate date) {
        return tasks
                .groupBy {it.getDateTimeStart().toLocalDate() }
                .find {it.getKey() == date }.getValue()
    }

    private void validateTimeBoarders(Task task, Action action) {
        def resultBoarders = validateDateBoarders(task.getDateTimeStart(), action.getStartAction(),
                task.getDateTimeEnd(), action.getEndAction())

        if (!resultBoarders) {
            throw new RuntimeException("Time boarders in action ${action.getName()} " +
                    "not allowed for task ${task.getName()}")
        }
    }

    private void validatePeriods(List<Task> tasks, Task task) {
        if (task.getDateTimeEnd().isBefore(task.getDateTimeStart())) {
            throw new RuntimeException("Task ${task.getName()}: " +
                    "The end date cannot be less than the start date")
        }

        if (tasks != null && !tasks.isEmpty()) {
            tasks.forEach {
                def resultDates = validatePeriodDates(task.getDateTimeStart(), it.getDateTimeStart(),
                        task.getDateTimeEnd(), it.getDateTimeEnd())

                if (resultDates) {
                    throw new RuntimeException("Task ${task.getName()}: " +
                            "This period of time is busy that task: ${it.getName()}")
                }
            }
        }
    }

    private void validatePeriods(List<Action> actions, Action action) {
        if (action.getEndAction().isBefore(action.getStartAction())) {
            throw new RuntimeException("Action ${action.getName()}: " +
                    "The end date cannot be less than the start date")
        }

        if (actions != null && !actions.isEmpty()) {
            actions.forEach {
                def resultDates = validatePeriodDates(action.getStartAction(), it.getStartAction(),
                        action.getEndAction(), it.getEndAction())

                if (resultDates) {
                    throw new RuntimeException("Action ${action.getName()}: " +
                            "This period of time is busy that action: ${it.getName()}")
                }
            }
        }
    }

    private long periodOfSeconds(LocalDateTime date1, LocalDateTime date2) {
        return ChronoUnit.SECONDS.between(date1, date2)
    }

    private static boolean validatePeriodDates(LocalDateTime startDate1, LocalDateTime startDate2,
                                               LocalDateTime endDate1, LocalDateTime endDate2) {
        def firstPeriod = startDate1 < startDate2 &&
                startDate2 < endDate1
        def secondPeriod = startDate1 < endDate2 &&
                endDate2 < endDate1
        def thirdPeriod = startDate1 > startDate2 &&
                endDate1 < endDate2
        def fourthPeriod = startDate1 < startDate2 &&
                endDate2 < endDate1

        return firstPeriod || secondPeriod || thirdPeriod || fourthPeriod
    }

    private static boolean validateDateBoarders(LocalDateTime startDate1, LocalDateTime startDate2,
                                                LocalDateTime endDate1, LocalDateTime endDate2) {
        def period1 = startDate2 > startDate1 && startDate2 < endDate1
        def period2 = endDate2 > startDate1 && endDate2 < endDate1

        return period1 && period2
    }
}