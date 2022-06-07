package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_animal.*
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.AnimalRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels.CreateAnimalViewModel


class CreateAnimalActivity : AppCompatActivity() {
    lateinit var viewModel: CreateAnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_animal)
        viewModel = ViewModelProvider(this).get(CreateAnimalViewModel::class.java)
        createAnimalObservable()
        createButton.setOnClickListener {
            createAnimal()
        }
    }

    private fun createAnimalObservable() {
        viewModel.getCreateNewAnimalObservable().observe(this, Observer <AnimalResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateAnimalActivity, "Failed to create new animal...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateAnimalActivity, "Successfully created animal...", Toast.LENGTH_LONG).show()
                val refresh = Intent(this, AnimalActivity::class.java)
                startActivity(refresh)
                finish()
            }
        })
    }

    fun createAnimal(){
        val request = AnimalRequest(editTextName.text.toString(), editTextAge.text.toString().toInt(),
            editTextKind.text.toString())
        viewModel.createAnimal(applicationContext, request)
    }
}