package ar.com.damianfrutos.apunte7activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.com.damianfrutos.apunte7activity.ui.theme.Apunte7ActivityTheme

class EmailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email_activity)
        //Inicializo las variables
        val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val btEnviar = findViewById<Button>(R.id.btEnviar)


        //Cuando presiono el botón (método setOnclickListener) ejecuto la función enviarCorreo.
        btEnviar.setOnClickListener {
            val destinatario = editTextTextEmailAddress.text.toString()
            val mensaje = "Mensaje del correo feriohfriohguierghui"
            val asunto = "Asunto del correo ioefjweiojfiowehfuiwehfui"
            enviarCorreo(destinatario,asunto, mensaje)
        }

    }

    //Envio de correo con INTENT (utilizamos para enviar una aplicación de correo que se
    //encuentre instalada en el teléfono).
    private fun enviarCorreo(destinatario: String, asunto: String, mensaje: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Solo aplicaciones de correo deben manejar esto
            putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
            putExtra(Intent.EXTRA_SUBJECT, asunto)
            putExtra(Intent.EXTRA_TEXT, mensaje)
        }
        try {
            startActivity(Intent.createChooser(intent, "Elige una aplicación de correo"))
        } catch (e: Exception) {
            Toast.makeText(this, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show()
        }
    }
}
