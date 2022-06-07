package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.animal_layout.view.*
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.R
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui.EditAnimalActivity
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.ui.fragments.DeleteAnimalDialog
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.AnimalManager


class AnimalAdapter(private val context: Context, var animalList: MutableList<Animal>):
    RecyclerView.Adapter<AnimalAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_id: TextView = itemView.txt_id
        val txt_name: TextView = itemView.txt_name
        val txt_age: TextView = itemView.txt_age
        val txt_kind: TextView = itemView.txt_kind

        fun bind(listItem: Animal) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
            }
            itemView.btnDel.setOnClickListener {
                Toast.makeText(it.context, "хочет удалить ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
                val animalManager = AnimalManager(it.context)
                animalManager.saveId(itemView.txt_id.text.toString())
                val deleteAnimalDialog: DeleteAnimalDialog = DeleteAnimalDialog(itemView.txt_id.text.toString())
                val ft: FragmentTransaction = (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                deleteAnimalDialog.show(ft, "deleteDialog")
            }
            itemView.btnEdit.setOnClickListener{
                var intent = Intent(it.context, EditAnimalActivity::class.java)
                intent.putExtra("id", itemView.txt_id.text.toString())
                intent.putExtra("name", itemView.txt_name.text.toString())
                intent.putExtra("age", itemView.txt_age.text.toString())
                intent.putExtra("kind", itemView.txt_kind.text.toString())
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = animalList[position]
        holder.bind(listItem)

        holder.txt_id.text = animalList[position].id.toString()
        holder.txt_name.text = animalList[position].name
        holder.txt_age.text = animalList[position].age.toString()
        holder.txt_kind.text = animalList[position].kind
    }

}