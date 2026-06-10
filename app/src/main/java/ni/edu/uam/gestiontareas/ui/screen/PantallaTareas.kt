package ni.edu.uam.gestiontareas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

    var filtro by remember {
        mutableStateOf("TODAS")
    }

    val pendientes = lista.count { !it.completada }

    val porcentaje =
        if (lista.isEmpty()) {
            0
        } else {
            (lista.count { it.completada } * 100) / lista.size
        }

    val tareasMostrar = when (filtro) {
        "PENDIENTES" -> lista.filter { !it.completada }
        "COMPLETADAS" -> lista.filter { it.completada }
        else -> lista
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
                text = "📋 Gestión de Tareas",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            )

            Text(
                text = "Organiza tus actividades diarias",
                color = Color(0xFF607D8B)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD9ECFF)
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Título de la tarea",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = {
                            titulo = it
                        },
                        placeholder = {
                            Text(
                                "Escribe una tarea..."
                            )
                        },
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .testTag("campoTitulo"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1E88E5),
                            unfocusedBorderColor = Color(0xFF90CAF9),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {

                            if (titulo.trim().isNotEmpty()) {

                                gestor.agregarTarea(
                                    Tarea(
                                        id = lista.size + 1,
                                        titulo = titulo,
                                        descripcion = ""
                                    )
                                )

                                lista = gestor.obtenerTodas().toList()

                                titulo = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .testTag("btnAgregar"),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF42A5F5)
                        )
                    ) {

                        Text(
                            text = "➕ Agregar Tarea",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Pendientes: $pendientes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1565C0),
                    modifier = Modifier.testTag("txtPendientes")
                )

                Text(
                    text = "Completado: $porcentaje%",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1565C0)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        filtro = "TODAS"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (filtro == "TODAS")
                                Color(0xFF42A5F5)
                            else
                                Color(0xFFD6ECFF),

                        contentColor =
                            if (filtro == "TODAS")
                                Color.White
                            else
                                Color(0xFF1565C0)
                    )
                ) {
                    Text("Todas")
                }

                Button(
                    onClick = {
                        filtro = "PENDIENTES"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (filtro == "PENDIENTES")
                                Color(0xFF42A5F5)
                            else
                                Color(0xFFD6ECFF),

                        contentColor =
                            if (filtro == "PENDIENTES")
                                Color.White
                            else
                                Color(0xFF1565C0)
                    )
                ) {
                    Text("Pendientes")
                }

                Button(
                    onClick = {
                        filtro = "COMPLETADAS"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (filtro == "COMPLETADAS")
                                Color(0xFF42A5F5)
                            else
                                Color(0xFFD6ECFF),

                        contentColor =
                            if (filtro == "COMPLETADAS")
                                Color.White
                            else
                                Color(0xFF1565C0)
                    )
                ) {
                    Text("Completadas")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    lista = lista.sortedBy {
                        it.titulo.lowercase()
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD6ECFF),
                    contentColor = Color(0xFF1565C0)
                )
            ) {

                Text(
                    "Ordenar A-Z",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {

                items(tareasMostrar) { tarea ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (tarea.completada)
                                    Color(0xFFE5F7EA)
                                else
                                    Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {

                            Text(
                                text = tarea.titulo,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF263238)
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text =
                                    if (tarea.completada)
                                        "✅ Completada"
                                    else
                                        "⏳ Pendiente",
                                color = Color.DarkGray
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Row {

                                Button(
                                    onClick = {

                                        gestor.completarTarea(tarea.id)

                                        lista = gestor
                                            .obtenerTodas()
                                            .map { it.copy() }

                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFD6ECFF),
                                        contentColor = Color(0xFF1565C0)
                                    )
                                ) {

                                    Text("Completar")
                                }

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Button(
                                    onClick = {

                                        gestor.eliminarTarea(tarea.id)

                                        lista = gestor
                                            .obtenerTodas()
                                            .map { it.copy() }

                                    },
                                    modifier = Modifier.testTag(
                                        "eliminar_${tarea.id}"
                                    ),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFD6ECFF),
                                        contentColor = Color(0xFF1565C0)
                                    )
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