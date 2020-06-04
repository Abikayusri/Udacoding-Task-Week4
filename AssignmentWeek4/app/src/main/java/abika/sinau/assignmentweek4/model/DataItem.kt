package abika.sinau.assignmentweek4.model

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("mahasiswa_alamat")
	val mahasiswaAlamat: String? = null,

	@field:SerializedName("mahasiswa_nohp")
	val mahasiswaNohp: String? = null,

	@field:SerializedName("id_mahasiswa")
	val idMahasiswa: String? = null,

	@field:SerializedName("mahasiswa_nama")
	val mahasiswaNama: String? = null
)