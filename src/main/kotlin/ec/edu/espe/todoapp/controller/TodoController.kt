package ec.edu.espe.todoapp.controller

import ec.edu.espe.todoapp.model.DescriptiveTask
import ec.edu.espe.todoapp.model.SimpleTask
import ec.edu.espe.todoapp.model.Task
import ec.edu.espe.todoapp.service.ITaskService
import ec.edu.espe.todoapp.service.TaskService
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

class TodoController {
    private val scan = Scanner(System.`in`)
    private val taskService: ITaskService = TaskService()

    fun main() {
        val menuOptions = listOf(
            "Add new task",
            "Update tasks",
            "View tasks list",
            "Exit"
        )

        loop@do {
            menuOptions.forEachIndexed { index: Int, option: String ->
                println("${index + 1}: $option")
            }

            print("\nChoose an option: ")
            var input = 0

            try {
                input = scan.nextLine().trim().toInt()
            } catch (exception: NumberFormatException) {}

            when(input) {
                1 -> addNewTask()
                2 -> updateTask()
                3 -> showTasksList()
                4 -> break@loop
            }

            println()
            println()
        } while (true)
    }

    private fun addNewTask() {
        val tasks = taskService.getAllTasks()
        val id: Int = tasks.size + 1
        var hour: LocalTime
        var date: LocalDate
        val done = false
        var taskType = 0

        do {

            println("1: simple task")
            println("2: descriptive task")
            println()

            try {
                taskType = scan.nextLine().trim().toInt()
            } catch (exception: Exception) {
                continue;
            }
        } while (taskType < 1 || taskType > 2)

        print("Enter task title: ")
        val title: String = scan.nextLine()

        do {
            try {
                print("Enter hour (HH:mm): ")
                val hourText = scan.nextLine()
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                hour = LocalTime.parse(hourText, timeFormatter)
            } catch (exception: Exception) {
                continue
            }

            break
        } while (true)

        do {
            try {
                print("Enter date (dd/mm/yyyy) [leave empty for current date]: ")
                val dateText = scan.nextLine()
                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                date = if (!dateText.isEmpty()) {
                    LocalDate.parse(dateText, dateFormatter)
                } else {
                    LocalDate.now()
                }
            } catch (exception: Exception) {
                continue
            }

            break
        } while (true)


        val task: Task = if (taskType == 1) {
            SimpleTask(id, title, hour, date, done)
        } else {
            println("Enter a description: ")
            val description = scan.nextLine()
            DescriptiveTask(id, title, description, hour, date, done)
        }

        taskService.addTask(task)
    }

    private fun updateTask() {
        loop@while (true) {
            showTasksList()
            println()
            print("Enter task id for toggle its status or -1 to exit to main menu: ")
            var id = 0

            try {
                id = scan.nextLine().trim().toInt()

                if (id != -1 && (id < 1 || id > taskService.getAllTasks().size)) {
                    throw Exception("invalid task id")
                } else if (id == -1) {
                    break@loop
                }
            } catch (exception: Exception) {
                continue
            }

            val task = taskService.getTask(id)
            task.done = !task.done
        }
    }

    private fun showTasksList() {
        taskService.getAllTasks().forEach { it.print() }
    }
}
