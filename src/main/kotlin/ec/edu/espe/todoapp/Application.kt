package ec.edu.espe.todoapp

import ec.edu.espe.todoapp.controller.TodoController

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val controller: TodoController = TodoController()
        controller.main()
    }
}
