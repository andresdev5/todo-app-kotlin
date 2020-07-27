package ec.edu.espe.todoapp.model

import java.time.LocalDate
import java.time.LocalTime

abstract class Task(
    private var _id: Int,
    private var _title: String,
    private var _hour: LocalTime,
    private var _date: LocalDate,
    private var _done: Boolean) {

    var id: Int
        get() = this._id
        set(value: Int) { this._id = value }

    var title: String
        get() = this._title
        set(value: String) { this._title = value }

    var hour: LocalTime
        get() = this._hour
        set(value: LocalTime) { this._hour = value }

    var date: LocalDate
        get() = this._date
        set(value: LocalDate) { this._date = value }

    var done: Boolean
        get() = this._done
        set(value: Boolean) { this._done = value }

    abstract fun print()
}