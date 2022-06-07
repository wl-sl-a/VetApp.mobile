package solianyk_anastasiia_romanivna.pzpi_18_6.vetappmobile.api.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginResponse (
    @SerializedName("expiration")
    var expiration: Date,

    @SerializedName("token")
    var token: String
)