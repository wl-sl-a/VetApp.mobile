package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses

import com.google.gson.annotations.SerializedName

data class OwnerResponse (
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
    var username: String? = null,

    @SerializedName("vetName")
    var vetName: String? = null
)