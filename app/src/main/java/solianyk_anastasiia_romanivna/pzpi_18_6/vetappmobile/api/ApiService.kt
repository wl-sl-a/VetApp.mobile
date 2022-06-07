package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api

import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.LoginRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.LoginResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.utils.Constants
import retrofit2.Call
import retrofit2.http.*
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests.AnimalRequest
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.AnimalResponse
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses.OwnerResponse

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.ANIMAL_URL)
    fun fetchAnimals(): Call<MutableList<Animal>>

    @GET(Constants.ANIMAL_URL+"/{id}")
    fun getAnimal(@Path("id") id: Int): Call<AnimalResponse>

    @GET(Constants.ANIMAL_URL+"/search/{param}")
    fun searchAnimal(@Path("param") param: String): Call<MutableList<Animal>>

    @POST(Constants.ANIMAL_URL)
    fun addAnimal(@Body request: AnimalRequest): Call<AnimalResponse>

    @PUT(Constants.ANIMAL_URL+"/{id}")
    fun editAnimal(@Path("id") id: Int, @Body request: AnimalRequest): Call<AnimalResponse>

    @DELETE(Constants.ANIMAL_URL+"/{id}")
    fun deleteAnimal(@Path("id") id: Int): Call<AnimalResponse>

    @GET(Constants.OWNER_URL)
    fun getOwner(): Call<OwnerResponse>
}