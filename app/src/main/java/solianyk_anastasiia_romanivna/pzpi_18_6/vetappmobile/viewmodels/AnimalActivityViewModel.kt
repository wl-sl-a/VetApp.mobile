package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.repositories.AnimalRepository

class AnimalActivityViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Animal>>
    var deletedAnimalLiveData: MutableLiveData<AnimalResponse?>
    var repository: AnimalRepository

    init {
        recyclerListData = MutableLiveData()
        deletedAnimalLiveData = MutableLiveData()
        repository = AnimalRepository()
    }

    fun getAnimalListObserverable() : MutableLiveData<MutableList<Animal>> {
        return recyclerListData
    }

    fun getAnimalsList(context: Context) {
        repository.getAnimalsList(context)
            .enqueue(object : Callback<MutableList<Animal>> {
                override fun onFailure(call: Call<MutableList<Animal>>, t: Throwable) {
                    recyclerListData.postValue(null)
                }
                override fun onResponse(call: Call<MutableList<Animal>>, response: Response<MutableList<Animal>>) {
                    if(response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }

    fun search(context: Context, param: String) {
        repository.searchAnimal(context, param)
            .enqueue(object : Callback<MutableList<Animal>> {
                override fun onFailure(call: Call<MutableList<Animal>>, t: Throwable) {
                    recyclerListData.postValue(null)
                }
                override fun onResponse(call: Call<MutableList<Animal>>, response: Response<MutableList<Animal>>) {
                    if(response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }

    fun deleteAnimalObserverable(): MutableLiveData<AnimalResponse?>{
        return deletedAnimalLiveData
    }

    fun deleteAnimal(context: Context, aquariumId: Int){
        repository.deleteAnimal(context, aquariumId)
            .enqueue(object : Callback<AnimalResponse?> {
                override fun onFailure(call: Call<AnimalResponse?>, t: Throwable) {
                    deletedAnimalLiveData.postValue(null)
                }

                override fun onResponse(call: Call<AnimalResponse?>, response: Response<AnimalResponse?>) {
                    if(response.isSuccessful) {
                        deletedAnimalLiveData.postValue(response.body())
                    } else {
                        deletedAnimalLiveData.postValue(null)
                    }
                }
            })
    }
}