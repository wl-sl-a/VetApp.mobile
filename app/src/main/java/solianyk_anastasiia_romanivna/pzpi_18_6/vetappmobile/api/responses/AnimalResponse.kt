package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses

import com.google.gson.annotations.SerializedName
import solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models.Animal

data class AnimalResponse (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("ownerId")
    var ownerId: Int? = null,

    @SerializedName("age")
    var age: Int? = null,

    @SerializedName("kind")
    var kind: String? = null,
)