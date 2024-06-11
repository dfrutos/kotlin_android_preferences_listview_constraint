package ar.com.damianfrutos.apunte7activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var myPreferences: MyPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* CONSTRAINT LAYOUT */

            //setContentView(R.layout.main_activity_constraint_guideline)



        /* LISTVIEW */

            //Cargamos el layout que contiene el listview
            setContentView(R.layout.main_activity_listview)

            //Creamos una variable de listview y la enlazamos con el listview del layout (main_activity_listview)
            val listView: ListView = findViewById(R.id.listView)

            //Creamos una lsita con elementos
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

            //Creamos el adaptador (parametros: contexto, el layout que contiene el diseño de cada item del list view,
            //el textview del listview (podríamos tener más de un textview), y la lista
            val adapter = ArrayAdapter(this, R.layout.item_layout, R.id.textViewItem, items)

            //Finalmente seteamos el adapter del listview
            listView.adapter = adapter



        /* TOAST */

        //Vamos a combinar el evento setonItemClickListener para capturar la opción presionada del
        //listview, con un toast que indique cuál es el elemento que se presionó

        listView.setOnItemClickListener { parent, view, position, id ->
            // Obtener el elemento seleccionado
            val selectedItem = parent.getItemAtPosition(position) as String
            // Mostrar un Toast con el elemento seleccionado
            Toast.makeText(this, "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()
        }


        /* Shared Preferences */
        myPreferences = MyPreferences(this)
        val editText = findViewById<EditText>(R.id.editText)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonLoad = findViewById<Button>(R.id.buttonLoad)
        val buttonSaveList = findViewById<Button>(R.id.buttonSaveList)
        val buttonLoadList = findViewById<Button>(R.id.buttonLoadList)

        buttonSave.setOnClickListener {
            val text = editText.text.toString()
            myPreferences.saveData("key", text)
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
        }

        buttonLoad.setOnClickListener {
            val text = myPreferences.getData("key", "Default Value")
            Toast.makeText(this, "Mostrar datos: $text", Toast.LENGTH_SHORT).show()
        }

        buttonSaveList.setOnClickListener {
            val list = listOf("Item 1", "Item 2", "Item 3")
            myPreferences.saveList("keyList", list)
            Toast.makeText(this, "Lista guardada", Toast.LENGTH_SHORT).show()
        }

        buttonLoadList.setOnClickListener {
            val list = myPreferences.getList("keyList") ?: listOf("No Data")
            Toast.makeText(this, "Mostrar lista: $list", Toast.LENGTH_SHORT).show()
        }
    }

    /* MENU */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_option1 -> {
                showToast("Opción 1 seleccionada")
                true
            }
            R.id.action_option2 -> {
                showToast("Opción 2 seleccionada")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
