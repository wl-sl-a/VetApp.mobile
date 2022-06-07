package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests

import com.google.gson.annotations.SerializedName

data class AnimalRequest(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("age")
    var age: Int? = null,

    @SerializedName("kind")
    var kind: String? = null
)