package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils

import android.content.Context
import android.content.SharedPreferences
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R

class AnimalManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val ANIMAL_ID = "animal_id"
    }

    fun saveId(id: String) {
        val editor = prefs.edit()
        editor.putString(ANIMAL_ID, id)
        editor.apply()
    }

    fun fetchId(): String? {
        return prefs.getString(ANIMAL_ID, null)
    }
}