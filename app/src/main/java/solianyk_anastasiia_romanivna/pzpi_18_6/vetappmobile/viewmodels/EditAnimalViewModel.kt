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

class EditAnimalViewModel: ViewModel() {
    var editAnimalLiveData: MutableLiveData<AnimalResponse?>
    var repository: AnimalRepository

    init {
        editAnimalLiveData = MutableLiveData()
        repository = AnimalRepository()
    }

    fun editAnimalObservable(): MutableLiveData<AnimalResponse?> {
        return editAnimalLiveData
    }

    fun editAnimal(context: Context, aquariumId: Int, request: AnimalRequest) {
        repository.editAnimal(context, aquariumId, request).enqueue(object : Callback<AnimalResponse?> {
            override fun onFailure(call: Call<AnimalResponse?>, t: Throwable) {
                editAnimalLiveData.postValue(null)
            }
            override fun onResponse(call: Call<AnimalResponse?>, response: Response<AnimalResponse?>) {
                if(response.isSuccessful) {
                    editAnimalLiveData.postValue(response.body())
                } else {
                    editAnimalLiveData.postValue(null)
                }
            }
        })
    }
}