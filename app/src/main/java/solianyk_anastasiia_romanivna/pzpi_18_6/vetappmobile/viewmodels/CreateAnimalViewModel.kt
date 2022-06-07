package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.AnimalRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.repositories.AnimalRepository

class CreateAnimalViewModel: ViewModel() {
    lateinit var createNewAnimalLiveData: MutableLiveData<AnimalResponse?>
    lateinit var repository: AnimalRepository

    init {
        createNewAnimalLiveData = MutableLiveData()
        repository = AnimalRepository()
    }

    fun getCreateNewAnimalObservable(): MutableLiveData<AnimalResponse?> {
        return  createNewAnimalLiveData
    }

    fun createAnimal(context: Context, request: AnimalRequest) {
        repository.createAnimal(context, request).enqueue(object : Callback<AnimalResponse?> {
            override fun onFailure(call: Call<AnimalResponse?>, t: Throwable) {
                createNewAnimalLiveData.postValue(null)
            }
            override fun onResponse(call: Call<AnimalResponse?>, response: Response<AnimalResponse?>) {
                if(response.isSuccessful) {
                    createNewAnimalLiveData.postValue(response.body())
                } else {
                    createNewAnimalLiveData.postValue(null)
                }
            }
        })
    }
}