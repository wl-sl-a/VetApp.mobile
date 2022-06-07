package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_animal.*
import kotlinx.android.synthetic.main.activity_edit_animal.*
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.AnimalRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels.EditAnimalViewModel


class EditAnimalActivity : AppCompatActivity() {
    lateinit var viewModel: EditAnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_animal)
        var id = getIntent().getExtras()?.getString("id");
        var name = getIntent().getExtras()?.getString("name");
        var age = getIntent().getExtras()?.getString("age");
        var kind = getIntent().getExtras()?.getString("kind");

        val editTextId = findViewById<View>(R.id.editId) as EditText
        editTextId.setText(id)
        val editTextName = findViewById<View>(R.id.editName) as EditText
        editTextName.setText(name)
        val editTextAge = findViewById<View>(R.id.editAge) as EditText
        editTextAge.setText(age)
        val editTextKind = findViewById<View>(R.id.editKind) as EditText
        editTextKind.setText(kind)

        viewModel = ViewModelProvider(this).get(EditAnimalViewModel::class.java)

        editAnimalObservable()
        editButton.setOnClickListener {
            editAnimal()
        }
    }

    private fun editAnimalObservable() {
        viewModel.editAnimalObservable().observe(this, Observer <AnimalResponse?>{
            if(it == null) {
                Toast.makeText(this@EditAnimalActivity, "Failed to update new animal...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@EditAnimalActivity, "Successfully updated animal...", Toast.LENGTH_LONG).show()
                val refresh = Intent(this, AnimalActivity::class.java)
                startActivity(refresh)
                finish()
            }
        })
    }

    fun editAnimal(){
        val request = AnimalRequest(editName.text.toString(), editAge.text.toString().toInt(),
            editKind.text.toString())
        viewModel.editAnimal(applicationContext, editId.text.toString().toInt(), request)
    }
}