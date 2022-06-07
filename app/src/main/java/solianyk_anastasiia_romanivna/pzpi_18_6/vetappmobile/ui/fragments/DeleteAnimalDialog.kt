package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels.AnimalActivityViewModel


class DeleteAnimalDialog(val idAnimal: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
                .setMessage("Do you really want to delete the animal number "+idAnimal+"?")
                .setCancelable(true)
                .setPositiveButton("YES") { dialog, id ->
                    deleteAnimal(idAnimal.toInt(), activity, it.applicationContext)
                }
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Aquarium is not deleted",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun deleteAnimal(id: Int, activity: FragmentActivity?, context: Context) {
        lateinit var viewModel: AnimalActivityViewModel
        viewModel = ViewModelProvider(this).get(AnimalActivityViewModel::class.java)
        viewModel.deleteAnimalObserverable().observe(this, Observer <AnimalResponse?>{
            if(it == null) {
                Toast.makeText(activity, "Failed to delete animal...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Successfully deleted animal...", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.deleteAnimal(context, id)
        activity?.finish()
    }
}