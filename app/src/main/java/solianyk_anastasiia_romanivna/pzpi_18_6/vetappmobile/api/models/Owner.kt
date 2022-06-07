package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.models

import com.google.gson.annotations.SerializedName

class Owner(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("surname")
    var surname: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var username: String? = null
)