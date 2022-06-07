package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.SessionManager
import kotlinx.android.synthetic.main.activity_menu.*

import java.util.*

class MenuActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        sessionManager = SessionManager(this)

        animalButton.setOnClickListener {
            val intent = Intent(applicationContext, AnimalActivity::class.java)
            startActivity(intent);
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            LogOut()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun LogOut(){
        sessionManager = SessionManager(this)
        sessionManager.terminateSession()

        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent);
        this@MenuActivity.finish()
    }
}