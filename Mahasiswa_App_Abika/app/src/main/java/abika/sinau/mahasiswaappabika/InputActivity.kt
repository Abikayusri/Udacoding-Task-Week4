package abika.sinau.mahasiswaappabika

import abika.sinau.mahasiswaappabika.model.DataItem
import abika.sinau.mahasiswaappabika.model.ResponseAction
import abika.sinau.mahasiswaappabika.network.NetworkModule
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        val getData = intent.getParcelableExtra<DataItem>("Data")

        if (getData != null) {
            etNama.setText(getData.mahasiswaNama)
            etNoHp.setText(getData.mahasiswaNohp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btnSave.text = "Update"
            btnSave.setBackgroundResource(R.drawable.bg_button_update)
        }

        btnSave.setOnClickListener {
            when (btnSave.text) {
                "Update" -> {
                    when {
                        (etNama.text.isEmpty() && etNoHp.text.isEmpty() && etAlamat.text.isEmpty()) -> {
                            etNama.error = "Nama tidak boleh kosong"
                            etNoHp.error = "NoHp tidak boleh kosong"
                            etAlamat.error = "Alamat tidak boleh kosong"
                        }
                        (etNama.text.isEmpty()) -> {
                            etNama.error = "Nama tidak boleh kosong"
                        }
                        (etAlamat.text.isEmpty()) -> {
                            etAlamat.error = "Alamat tidak boleh kosong"
                        }
                        (etNoHp.text.isEmpty()) -> {
                            etNoHp.error = "NoHp tidak boleh kosong"
                        }
                        else -> {
                            updateData(
                                getData?.idMahasiswa,
                                etNama.text.toString(),
                                etNoHp.text.toString(),
                                etAlamat.text.toString()
                            )
                        }
                    }
                }
                else -> {
                    when {
                        (etNama.text.isEmpty() && etNoHp.text.isEmpty() && etAlamat.text.isEmpty()) -> {
                            etNama.error = "Nama tidak boleh kosong"
                            etNoHp.error = "NoHp tidak boleh kosong"
                            etAlamat.error = "NoHp tidak boleh kosong"
                        }
                        (etNama.text.isEmpty()) -> {
                            etNama.error = "Nama tidak boleh kosong"
                        }
                        (etAlamat.text.isEmpty()) -> {
                            etAlamat.error = "Alamat tidak boleh kosong"
                        }
                        (etNoHp.text.isEmpty()) -> {
                            etNoHp.error = "NoHp tidak boleh kosong"
                        }
                        else -> {
                            inputData(
                                etNama.text.toString(),
                                etNoHp.text.toString(),
                                etAlamat.text.toString()
                            )
                        }
                    }
                }
            }
        }

        btnBatal.setOnClickListener {
            finish()
        }
    }

    private fun inputData(nama: String?, noHp: String?, alamat: String?) {

        val input = NetworkModule.service().insertData(nama ?: "", noHp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                val status = response.body()?.isSuccess

                if (status == true) {
                    Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(
                        "Input Data", "Data diinput: ${
                        etNama.text.toString() + etNoHp.text.toString() + etAlamat.text.toString()
                        }"
                    )
                    finish()
                } else {
                    Toast.makeText(this@InputActivity, "Data tidak terinput", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(
                        "Input Data", "Datanya: ${
                        etNama.text.toString() + etNoHp.text.toString() + etAlamat.text.toString()
                        }"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?) {

        val getData = intent.getParcelableExtra<DataItem>("Data")
        val input =
            NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                val status = response.body()?.isSuccess

                if (status == true) {
                    Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(
                        "Update Data", "Data diupdate: ${
                        getData?.idMahasiswa + etNama.text.toString() + etNoHp.text.toString() + etAlamat.text.toString()
                        }"
                    )
                    finish()
                } else {
                    Toast.makeText(this@InputActivity, "Data tidak terinput", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(
                        "Update Data", "Datanya: ${
                        getData?.idMahasiswa + etNama.text.toString() + etNoHp.text.toString() + etAlamat.text.toString()
                        }"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}