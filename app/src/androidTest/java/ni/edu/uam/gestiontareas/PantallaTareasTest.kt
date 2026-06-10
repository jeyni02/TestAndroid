package ni.edu.uam.gestiontareas

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import ni.edu.uam.gestiontareas.ui.screen.PantallaTareas
import org.junit.Rule
import org.junit.Test

class PantallaTareasTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun agregarTarea_apareceEnPantalla() {

        composeTestRule.setContent {
            PantallaTareas()
        }

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Estudiar Compose")

        composeTestRule
            .onNodeWithTag("btnAgregar")
            .performClick()

        composeTestRule
            .onNodeWithText("Estudiar Compose")
            .assertExists()
    }

    @Test
    fun botonAgregar_respondeAlClick() {

        composeTestRule.setContent {
            PantallaTareas()
        }

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Nueva tarea")

        composeTestRule
            .onNodeWithTag("btnAgregar")
            .performClick()

        composeTestRule
            .onNodeWithText("Nueva tarea")
            .assertExists()
    }

    @Test
    fun eliminarTarea_desapareceDeLaLista() {

        composeTestRule.setContent {
            PantallaTareas()
        }

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Eliminar prueba")

        composeTestRule
            .onNodeWithTag("btnAgregar")
            .performClick()

        composeTestRule
            .onNodeWithTag("eliminar_1")
            .performClick()

        composeTestRule
            .onNodeWithText("Eliminar prueba")
            .assertDoesNotExist()
    }

    @Test
    fun mostrarPendientes_esCorrecto() {

        composeTestRule.setContent {
            PantallaTareas()
        }

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Pendiente")

        composeTestRule
            .onNodeWithTag("btnAgregar")
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithText("Pendientes: 1")
            .onFirst()
            .assertExists()
    }

    @Test
    fun campoEntrada_aceptaTextoCorrectamente() {

        composeTestRule.setContent {
            PantallaTareas()
        }

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Android Studio")

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .assertExists()
    }
}