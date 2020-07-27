package ec.edu.espe.todoapp.service

import ec.edu.espe.todoapp.model.Task
import java.time.LocalDate
import java.time.LocalTime

interface ITaskService {
    fun getAllTasks(): ArrayList<Task>
    fun getTask(id: Int): Task
    fun addTask(task: Task)
    fun find(title: String): List<Task>
    fun find(words: Array<String>): List<Task>
}