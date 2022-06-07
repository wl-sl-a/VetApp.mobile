package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.repositories

import android.content.Context

import retrofit2.Call
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.ApiClient
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.AnimalRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse

class AnimalRepository {
    var apiClient: ApiClient;

    init{
        apiClient = ApiClient()
    }

    fun createAnimal(context: Context, request: AnimalRequest):Call<AnimalResponse>{
        return apiClient.getApiService(context).addAnimal(request)
    }

    fun getAnimalsList(context: Context):Call<MutableList<Animal>>{
        return apiClient.getApiService(context).fetchAnimals()
    }

    fun deleteAnimal(context: Context, animalId: Int): Call<AnimalResponse>{
        return apiClient.getApiService(context).deleteAnimal(animalId)
    }

    fun editAnimal(context: Context, animalId: Int, request: AnimalRequest): Call<AnimalResponse>{
        return apiClient.getApiService(context).editAnimal(animalId, request)
    }

    fun searchAnimal(context: Context, param: String): Call<MutableList<Animal>>{
        return apiClient.getApiService(context).searchAnimal(param)
    }
}