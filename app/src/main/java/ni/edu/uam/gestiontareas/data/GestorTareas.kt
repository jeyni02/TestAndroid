package ni.edu.uam.gestiontareas.data

import ni.edu.uam.gestiontareas.model.Tarea

class GestorTareas {

    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun completarTarea(id: Int) {

        val indice = tareas.indexOfFirst {
            it.id == id
        }

        if (indice != -1) {

            val tareaActual = tareas[indice]

            tareas[indice] = tareaActual.copy(
                completada = true
            )
        }
    }

    fun obtenerPendientes(): List<Tarea> {
        return tareas.filter { !it.completada }
    }

    fun contarPendientes(): Int {
        return tareas.count { !it.completada }
    }

    fun obtenerTodas(): List<Tarea> {
        return tareas
    }

    fun obtenerCompletadas(): List<Tarea> {
        return tareas.filter { it.completada }
    }

    fun ordenarAlfabeticamente(): List<Tarea> {
        return tareas.sortedBy { it.titulo }
    }

    fun porcentajeCompletadas(): Double {

        if (tareas.isEmpty()) {
            return 0.0
        }

        return tareas.count { it.completada } * 100.0 / tareas.size
    }
}