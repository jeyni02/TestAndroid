package ni.edu.uam.gestiontareas.model

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    var completada: Boolean = false
)