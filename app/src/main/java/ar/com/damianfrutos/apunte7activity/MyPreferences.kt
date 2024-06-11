package ar.com.damianfrutos.apunte7activity

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class MyPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun clearData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    // Guardar una lista
    fun saveList(key: String, list: List<String>) {
            val json = gson.toJson(list)
        val editor = sharedPreferences.edit()
        editor.putString(key, json)
        editor.apply()
    }

    fun getList(key: String): List<String>? {

        // La biblioteca Gson de Google se utiliza para convertir objetos Java a su representación JSON
        // y viceversa.
        // Para convertir una lista de objetos (por ejemplo, List<String>) desde o hacia JSON,
        // necesitamos proporcionar a Gson información sobre el tipo genérico de la lista. Esto es necesario porque en tiempo de ejecución, debido a la "Type Erasure" en Java, la información del tipo genérico se pierde.
        //Creación de un TypeToken Anónimo
        // object : TypeToken<List<String>>() {}: Esta parte del código crea una instancia anónima
        // de TypeToken para una lista de cadenas.
        // TypeToken es una clase de Gson que ayuda a preservar la información del tipo genérico.
        //.type: Este es un miembro de la clase TypeToken que devuelve un objeto Type que representa
        // el tipo genérico de List<String>.
        // La variable type resultante se utiliza para indicar a Gson cómo deserializar un JSON en
        // una lista de cadenas (List<String>).

        val json = sharedPreferences.getString(key, null) ?: return null
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}