package abika.sinau.mahasiswaappabika

import abika.sinau.mahasiswaappabika.adapter.MahasiswaAdapter
import abika.sinau.mahasiswaappabika.model.DataItem
import abika.sinau.mahasiswaappabika.model.ResponseAction
import abika.sinau.mahasiswaappabika.model.ResponseGetData
import abika.sinau.mahasiswaappabika.network.NetworkModule
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, InputActivity::class.java))
        }

        showData()
    }

    private fun showData() {
        val listMahasiswa =NetworkModule.service().getData()
        listMahasiswa.enqueue(object : Callback<ResponseGetData>{
            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if (response.isSuccessful){
                    Log.d("Main Activity", "Data berhasil")
                    val item = response.body()?.data
                    val status = response.body()?.isSuccess
                    if (status == true){
//                        val adapter = response.body()?.data
//                        rvMain.adapter = MahasiswaAdapter(adapter)
                        val adapter = MahasiswaAdapter(item, object : MahasiswaAdapter.OnClickListener{
                            override fun detail(item: DataItem?) {
                                val intent = Intent(this@MainActivity, InputActivity::class.java)
                                intent.putExtra("Data", item)
                                startActivity(intent)
                            }

                            override fun hapus(item: DataItem?) {
                                AlertDialog.Builder(this@MainActivity).apply {
                                    setTitle("Hapus Data")
                                    setMessage("Yakin mau hapus data ?")
                                    setPositiveButton("Hapus") { dialog, which ->
                                        hapusData(item?.idMahasiswa)
                                        dialog.dismiss()
                                    }
                                    setNegativeButton("Batal") { dialog, which ->
                                        dialog.dismiss()
                                    }
                                }.show()
                            }

                        })
                        rvMain.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    Log.e("Main Activity onFailure", response.message())
                }
            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Main Activity onFailure", t.message)
            }

        })
    }

    private fun hapusData(id: String?) {

        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data Berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()
                showData()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}
