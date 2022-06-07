package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)