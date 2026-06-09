package ni.edu.uam.gestiontareas

import ni.edu.uam.gestiontareas.data.GestorTareas
import ni.edu.uam.gestiontareas.model.Tarea
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GestorTareasTest {

    private lateinit var gestor: GestorTareas

    @Before
    fun setup() {
        gestor = GestorTareas()
    }

    @Test
    fun agregarTarea_incrementaLista() {

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Estudiar Kotlin",
                descripcion = "Pruebas unitarias"
            )
        )

        assertEquals(
            1,
            gestor.obtenerTodas().size
        )
    }

    @Test
    fun eliminarTarea_desapareceLista() {

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Estudiar",
                descripcion = ""
            )
        )

        gestor.eliminarTarea(1)

        assertEquals(
            0,
            gestor.obtenerTodas().size
        )
    }

    @Test
    fun completarTarea_cambiaEstado() {

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Proyecto Android",
                descripcion = ""
            )
        )

        gestor.completarTarea(1)

        assertTrue(
            gestor.obtenerTodas()[0].completada
        )
    }

    @Test
    fun contarPendientes_retornaValorCorrecto() {

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Tarea 1",
                descripcion = ""
            )
        )

        gestor.agregarTarea(
            Tarea(
                id = 2,
                titulo = "Tarea 2",
                descripcion = ""
            )
        )

        gestor.completarTarea(1)

        assertEquals(
            1,
            gestor.contarPendientes()
        )
    }

    @Test
    fun listaVacia_retornaCeroPendientes() {

        assertEquals(
            0,
            gestor.contarPendientes()
        )
    }
}