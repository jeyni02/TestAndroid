package ni.edu.uam.gestiontareas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ni.edu.uam.gestiontareas.data.GestorTareas
import ni.edu.uam.gestiontareas.model.Tarea

@Composable
fun PantallaTareas() {

    val gestor = remember {
        GestorTareas()
    }

    var titulo by remember {
        mutableStateOf("")
    }

    var lista by remember {
        mutableStateOf<List<Tarea>>(emptyList())
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEAF4FF)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Gestión de Tareas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Organiza tus actividades",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB3D9FF)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = {
                            titulo = it
                        },
                        label = {
                            Text("Título")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("campoTitulo")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {

                            if (titulo.isNotBlank()) {

                                gestor.agregarTarea(
                                    Tarea(
                                        id = lista.size + 1,
                                        titulo = titulo,
                                        descripcion = ""
                                    )
                                )

                                lista =
                                    gestor.obtenerTodas().toList()

                                titulo = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btnAgregar"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF64B5F6)
                        )
                    ) {

                        Text("Agregar Tarea")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pendientes: ${
                    lista.count { !it.completada }
                }",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                modifier = Modifier.testTag("txtPendientes")
            )

            Spacer(modifier = Modifier.height(8.dp))

            val porcentaje =
                if (lista.isEmpty()) 0
                else (lista.count { it.completada } * 100 / lista.size)

            Text(
                text = "Completado: $porcentaje%"
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {

                items(lista) { tarea ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (tarea.completada)
                                    Color(0xFFDFF5E1)
                                else
                                    Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = tarea.titulo,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    if (tarea.completada)
                                        "Completada"
                                    else
                                        "Pendiente"
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Row {

                                Button(
                                    onClick = {

                                        gestor.completarTarea(tarea.id)

                                        lista = gestor.obtenerTodas().map { it.copy() }

                                    }
                                ) {
                                    Text("Completar")
                                }

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Button(
                                    onClick = {

                                        gestor.eliminarTarea(tarea.id)

                                        lista = gestor.obtenerTodas().map { it.copy() }

                                    }
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}