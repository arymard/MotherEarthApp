package binar.co.id.simplekotlinretrofit.network


import com.example.user.motherearthbinar.BeritaResponse
import com.example.user.motherearthbinar.CuacaResponse
import com.example.user.motherearthbinar.GempaResponse
import retrofit2.Call
import retrofit2.http.GET


interface APIservice {
    @GET("api/v1/earthquakes")
    fun fetchAllGempa(): Call<GempaResponse>

    @GET("api/v1/news")
    fun fetchAllBerita() : Call<BeritaResponse>
    @GET("api/v1/weathers")
    fun fetchAllCuaca() : Call<CuacaResponse>
}