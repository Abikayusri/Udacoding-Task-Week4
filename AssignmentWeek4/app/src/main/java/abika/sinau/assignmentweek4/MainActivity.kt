package abika.sinau.assignmentweek4

import abika.sinau.assignmentweek4.adapter.MahasiswaAdapter
import abika.sinau.assignmentweek4.model.DataItem
import abika.sinau.assignmentweek4.model.ResponseGetData
import abika.sinau.assignmentweek4.network.NetworkModule
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            Toast.makeText(this, "Anda menekan toast", Toast.LENGTH_SHORT).show()
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
                        val adapter = response.body()?.data
                        rvMain.adapter = MahasiswaAdapter(adapter)
                    }
//                    val adapter = MahasiswaAdapter(item, object : MahasiswaAdapter.OnClickListener{
//                        override fun detail(item: DataItem?) {
//
//                        }
//
//                    })

//                    rvMain.adapter = MahasiswaAdapter(item)
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

    override fun onResume() {
        super.onResume()
        showData()
    }
}
