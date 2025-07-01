package com.example.miagenda

//MainActyvity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaVista()
        }
    }
}
@Composable
@Preview(showBackground = true)
fun AgendaVista() {
    val agenda = remember { Agenda() }
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var contactos by remember { mutableStateOf<List<Contacto>>(emptyList()) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            placeholder = { Text("Escribe tu nombre:") }
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        TextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            placeholder = { Text("Escribe tu teléfono:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Button(onClick = {
            if (nombre.isNotBlank() && telefono.isNotBlank()) {
                agenda.agregarContacto(Contacto(nombre, telefono.toInt()))
                nombre = ""
                telefono = ""
                contactos = agenda.contactos.toList() // Crear nueva lista para trigger recomposición
            }
        }) {
            Text("Guardar")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text("Lista de Contactos",)

        LazyColumn {
            items(contactos) { contacto ->
                Column(modifier = Modifier.fillMaxSize().background(color = Color.LightGray).padding(8.dp)) {
                    Text("${contacto.nombre}")
                    Text("${contacto.telefono}")
                }
            }
        }
    }


}
