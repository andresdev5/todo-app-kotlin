package ec.edu.espe.todoapp.model

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SimpleTask(
    _id: Int,
    _title: String,
    _hour: LocalTime,
    _date: LocalDate,
    _done: Boolean
) : Task(_id, _title, _hour, _date, _done) {
    override fun print() {
        val hourFormat = hour.format(DateTimeFormatter.ofPattern("HH:mm"))
        val dateFormat = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        println("${id}) $title | $hourFormat | $dateFormat | complete: [${if (done) "yes" else "no"}]")
    }
}