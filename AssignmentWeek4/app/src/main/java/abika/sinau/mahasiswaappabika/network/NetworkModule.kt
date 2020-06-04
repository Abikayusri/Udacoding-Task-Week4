package abika.sinau.mahasiswaappabika.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abika Chairul Yusri
 * on Thursday, 04 June 2020
 * Bismillahirrahmanirrahim
 */
object NetworkModule {

    fun getRetrofit(): Retrofit {

        return Retrofit.Builder().baseUrl("http://192.168.100.227/assignment-week4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}