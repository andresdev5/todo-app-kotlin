package ec.edu.espe.todoapp.service

import ec.edu.espe.todoapp.model.Task
import java.util.stream.Collectors

class TaskService : ITaskService {
    private val tasks: ArrayList<Task> = arrayListOf()

    override fun getAllTasks(): ArrayList<Task> = tasks

    override fun getTask(id: Int): Task {
        return tasks.stream()
            .filter { it.id == id }
            .findFirst()
            .orElse(null);
    }

    override fun find(title: String): List<Task> {
        return tasks.stream().filter { task ->
            task.title.trim().toLowerCase() == title
        }.collect(Collectors.toList())
    }

    override fun find(words: Array<String>): List<Task> {
        return tasks.stream().filter { task ->
            val taskWords = task.title.split(" ")

            taskWords.forEach { word ->
                if (words.contains(word)) {
                    return@filter true
                }
            }

            return@filter false
        }.collect(Collectors.toList())
    }

    override fun addTask(task: Task) {
        tasks.add(task)
    }
}
