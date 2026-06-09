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
        tareas.find { it.id == id }?.completada = true
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
}