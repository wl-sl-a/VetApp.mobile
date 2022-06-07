package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_animal.*
import kotlinx.android.synthetic.main.animal_layout.*
import kotlinx.android.synthetic.main.animal_layout.view.*
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui.adapters.AnimalAdapter
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.AnimalManager
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.SessionManager
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels.AnimalActivityViewModel

class AnimalActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: AnimalAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: AlertDialog

    lateinit var viewModel: AnimalActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        sessionManager = SessionManager(this)

        recyclerAnimalList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerAnimalList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        initViewModel()
        search()
        btnAdd.setOnClickListener {
            startActivity(Intent(this@AnimalActivity, CreateAnimalActivity::class.java))
            Toast.makeText(this@AnimalActivity, AnimalManager(this).fetchId(), Toast.LENGTH_LONG).show()
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
        this@AnimalActivity.finish()
    }

    private fun search() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(!TextUtils.isEmpty(searchEditText.text.toString())) {
                    viewModel.search(applicationContext, searchEditText.text.toString())
                } else {
                    viewModel.getAnimalsList(applicationContext)
                }
            }
        })
    }

    fun initViewModel(){
        dialog.show()
        viewModel = ViewModelProvider(this).get(AnimalActivityViewModel::class.java)
        viewModel.getAnimalListObserverable().observe(this, Observer<MutableList<Animal>> {
            if(it == null) {
                Toast.makeText(this@AnimalActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                adapter = AnimalAdapter(baseContext, it)
                adapter.notifyDataSetChanged()
                recyclerAnimalList.adapter = adapter
                dialog.dismiss()
            }
        })
        viewModel.getAnimalsList(applicationContext)
    }
}