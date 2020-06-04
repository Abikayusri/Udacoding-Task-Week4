package abika.sinau.mahasiswaappabika.network

import abika.sinau.mahasiswaappabika.model.ResponseAction
import abika.sinau.mahasiswaappabika.model.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Abika Chairul Yusri
 * on Thursday, 04 June 2020
 * Bismillahirrahmanirrahim
 */
interface ApiService {

    // Get Data
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>

    // Get Data By Id
    @GET("getData.php")
    fun getDataById(
        @Query("id_mahasiswa") id: String
    ): Call<ResponseGetData>

    // insert Data
    @FormUrlEncoded
    @POST("insertData.php")
    fun insertData(
        @Field("mahasiswa_nama") nama: String,
        @Field("mahasiswa_nohp") nohp: String,
        @Field("mahasiswa_alamat") alamat: String): Call<ResponseAction>

    // update
    @FormUrlEncoded
    @POST("updateData.php")
    fun updateData(
        @Field("id_mahasiswa") id: String,
        @Field("mahasiswa_nama") nama: String,
        @Field("mahasiswa_nohp") nohp: String,
        @Field("mahasiswa_alamat") alamat: String): Call<ResponseAction>

    // delete Data
    @FormUrlEncoded
    @POST("deleteData.php")
    fun deleteData(
        @Field("id_mahasiswa") id: String): Call<ResponseAction>
}