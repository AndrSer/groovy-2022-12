import org.junit.jupiter.api.Test

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

class TestUserActions {

    @Test
    void '001 test add tasks in taskList'() {
        TaskList userActions = new TaskListImpl()

        def taskName = "test-task-1"
        LocalDate testDateStart1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeStart1 = LocalTime.of(23, 00)
        LocalDate testDateEnd1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeEnd1 = LocalTime.of(23, 05)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task)
        def tasks = userActions.getTasks()
        assert tasks.size() == 1
        assert tasks.get(0).getName() == taskName
        assert tasks.get(0).getDateTimeStart() == LocalDateTime.of(testDateStart1, testTimeStart1)
        assert tasks.get(0).getDateTimeEnd() == LocalDateTime.of(testDateEnd1, testTimeEnd1)

        taskName = "test-task-2"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(23, 01)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 10)

        Task task1 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userActions.addTask(task1)
        })

        String expectedMessage = "Task ${task1.getName()}: This period of time is busy that task: ${task.getName()}"
        String actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        taskName = "test-task-3"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(22, 50)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 03)

        Task task2 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        exception = assertThrows(RuntimeException.class, () -> {
            userActions.addTask(task2)
        })

        expectedMessage = "Task ${task2.getName()}: This period of time is busy that task: ${task.getName()}"
        actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        taskName = "test-task-4"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(23, 02)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 04)

        Task task3 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        exception = assertThrows(RuntimeException.class, () -> {
            userActions.addTask(task3)
        })

        expectedMessage = "Task ${task3.getName()}: This period of time is busy that task: ${task.getName()}"
        actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        taskName = "test-task-5"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(22, 50)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 12)

        Task task4 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        exception = assertThrows(RuntimeException.class, () -> {
            userActions.addTask(task4)
        })

        expectedMessage = "Task ${task4.getName()}: This period of time is busy that task: ${task.getName()}"
        actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        taskName = "test-task-6"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(22, 50)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 00)

        Task task5 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task5)
        tasks = userActions.getTasks()
        assert tasks.size() == 2
        assert tasks.get(1).getName() == taskName
        assert tasks.get(1).getDateTimeStart() == LocalDateTime.of(testDateStart1, testTimeStart1)
        assert tasks.get(1).getDateTimeEnd() == LocalDateTime.of(testDateEnd1, testTimeEnd1)

        taskName = "test-task-7"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(23, 05)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 10)

        Task task6 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task6)
        tasks = userActions.getTasks()
        assert tasks.size() == 3
        assert tasks.get(2).getName() == taskName
        assert tasks.get(2).getDateTimeStart() == LocalDateTime.of(testDateStart1, testTimeStart1)
        assert tasks.get(2).getDateTimeEnd() == LocalDateTime.of(testDateEnd1, testTimeEnd1)
    }

    @Test()
    void '002 test add action in task'() {
        TaskList userActions = new TaskListImpl()

        def taskName = "test-task-1"
        LocalDate testDateStart1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeStart1 = LocalTime.of(23, 00)
        LocalDate testDateEnd1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeEnd1 = LocalTime.of(23, 30)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task)

        def actionName = "action1"
        def description = "action-description1"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(22, 56)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 45)

        Action action1 = createAction(actionName, description,
                testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userActions.addAction(task, action1)
        })

        def expectedMessage = "Time boarders in action ${action1.getName()} " +
                "not allowed for task ${task.getName()}"
        def actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(22, 00)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(22, 45)
        action1.setStartAction(LocalDateTime.of(testDateStart1, testTimeStart1))
        action1.setEndAction(LocalDateTime.of(testDateEnd1, testTimeEnd1))

        exception = assertThrows(RuntimeException.class, () -> {
            userActions.addAction(task, action1)
        })

        expectedMessage = "Time boarders in action ${action1.getName()} " +
                "not allowed for task ${task.getName()}"
        actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(23, 40)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 45)
        action1.setStartAction(LocalDateTime.of(testDateStart1, testTimeStart1))
        action1.setEndAction(LocalDateTime.of(testDateEnd1, testTimeEnd1))

        exception = assertThrows(RuntimeException.class, () -> {
            userActions.addTask(userActions.addAction(task, action1))
        })

        expectedMessage = "Time boarders in action ${action1.getName()} " +
                "not allowed for task ${task.getName()}"
        actualMessage = exception.getMessage()

        assertTrue(actualMessage == expectedMessage)

        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(23, 01)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(23, 05)
        action1.setStartAction(LocalDateTime.of(testDateStart1, testTimeStart1))
        action1.setEndAction(LocalDateTime.of(testDateEnd1, testTimeEnd1))

        userActions.addAction(task, action1)

        assert task.getActions().size() == 1
        assert task.getActions().get(0).getName() == action1.getName()
        assert task.getActions().get(0).getStartAction() == LocalDateTime.of(testDateStart1, testTimeStart1)
        assert task.getActions().get(0).getEndAction() == LocalDateTime.of(testDateEnd1, testTimeEnd1)
    }

    @Test
    void '003 test count tasks on date and busy time'() {
        TaskList userActions = new TaskListImpl()

        def taskName = "test-task-1"
        LocalDate testDateStart1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeStart1 = LocalTime.of(23, 00)
        LocalDate testDateEnd1 = LocalDate.of(2023, 03, 27)
        LocalTime testTimeEnd1 = LocalTime.of(23, 05)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task)

        taskName = "test-task-2"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(19, 00)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(19, 30)

        Task task1 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task1)

        taskName = "test-task-3"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(20, 00)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(20, 30)

        Task task2 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task2)

        taskName = "test-task-4"
        testDateStart1 = LocalDate.of(2023, 03, 26)
        testTimeStart1 = LocalTime.of(20, 00)
        testDateEnd1 = LocalDate.of(2023, 03, 26)
        testTimeEnd1 = LocalTime.of(20, 30)

        Task task3 = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userActions.addTask(task3)

        def tasks = userActions.getTaskList(LocalDate.of(2023, 03, 27))
        assert tasks.size() == 3
        assert tasks.get(0).getName() == "test-task-1"
        assert tasks.get(1).getName() == "test-task-2"
        assert tasks.get(2).getName() == "test-task-3"

        def count = userActions.countTasks(LocalDate.of(2023, 03, 27))
        assert count == 3

        def timeCout = userActions.busyTimeCount(LocalDate.of(2023, 03, 27))
        assert timeCout == LocalTime.of(1, 05)
    }

    @Test
    void '004 test delete task from list'() {
        TaskList userAction = new TaskListImpl()

        def taskName = "test-task"
        def testDateStart1 = LocalDate.of(2023, 03, 26)
        def testTimeStart1 = LocalTime.of(20, 00)
        def testDateEnd1 = LocalDate.of(2023, 03, 26)
        def testTimeEnd1 = LocalTime.of(20, 30)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)
        userAction.addTask(task)

        def tasks = userAction.getTasks()
        userAction.deleteTask(tasks.get(0))

        assert userAction.getTasks().isEmpty()
    }

    @Test
    void '005 test delete action'() {
        TaskList userAction = new TaskListImpl()

        def taskName = "test-task"
        def testDateStart1 = LocalDate.of(2023, 03, 27)
        def testTimeStart1 = LocalTime.of(20, 00)
        def testDateEnd1 = LocalDate.of(2023, 03, 27)
        def testTimeEnd1 = LocalTime.of(20, 30)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)
        userAction.addTask(task)

        def actionName = "action1"
        def description = "action-description1"
        testDateStart1 = LocalDate.of(2023, 03, 27)
        testTimeStart1 = LocalTime.of(20, 05)
        testDateEnd1 = LocalDate.of(2023, 03, 27)
        testTimeEnd1 = LocalTime.of(20, 20)

        Action action = createAction(actionName, description,
                testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userAction.addAction(task, action)

        def actions = userAction.getTasks().get(0).getActions()
        userAction.deleteAction(task, actions.get(0))

        assert userAction.getTasks().get(0).getActions().isEmpty()
    }

    @Test
    void '006 test check event'() {
        TaskList userAction = new TaskListImpl()

        def taskName = "test-task"
        def testDateStart1 = LocalDate.now()
        def testTimeStart1 = LocalTime.now()
        def testDateEnd1 = LocalDate.now()
        def testTimeEnd1 = LocalTime.now().plusHours(1)

        Task task = createTask(taskName, testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)
        userAction.addTask(task)

        def actionName = "action1"
        def description = "action-description1"
        testDateStart1 = LocalDate.now()
        testTimeStart1 = LocalTime.now().plusSeconds(10)
        testDateEnd1 = LocalDate.now()
        testTimeEnd1 = LocalTime.now().plusMinutes(30)

        Action action = createAction(actionName, description,
                testDateStart1, testTimeStart1, testDateEnd1, testTimeEnd1)

        userAction.addAction(task, action)

        Event event = createEvent("001", "event-1", action)

        while (true) {
            event.printMessageEvent()

            if (LocalDateTime.now().getSecond() == action.getStartAction().plusNanos(100).getSecond()) {
                break
            }
        }
    }

    private Task createTask(taskName, testDateStart1,
                            testTimeStart1, testDateEnd1, testTimeEnd1) {
        Task task = new Task()

        task.with {
            (name,
            dateTimeStart,
            dateTimeEnd) = [taskName,
                            LocalDateTime.of(testDateStart1, testTimeStart1),
                            LocalDateTime.of(testDateEnd1, testTimeEnd1)]
        }
        return task
    }

    private Action createAction(actionName, actionDescription, testDateStart1,
                                testTimeStart1, testDateEnd1, testTimeEnd1) {
        Action action = new Action()

        action.with {
            (name,
            description,
            startAction,
            endAction) = [actionName,
                            actionDescription,
                            LocalDateTime.of(testDateStart1, testTimeStart1),
                            LocalDateTime.of(testDateEnd1, testTimeEnd1)]
        }
        return action
    }

    private Event createEvent(id, name, action) {
        return new Event(id, name, action)
    }
}
