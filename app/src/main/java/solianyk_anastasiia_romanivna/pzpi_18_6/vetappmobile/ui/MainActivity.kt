package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.SessionManager

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        val intent: Intent
        if(sessionManager.fetchAuthToken() == null){
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent);
            finish()
        }else{
            intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent);
            finish()
        }
    }
}