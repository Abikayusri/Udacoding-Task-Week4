package abika.sinau.mahasiswaappabika.adapter

import abika.sinau.mahasiswaappabika.InputActivity
import abika.sinau.mahasiswaappabika.R
import abika.sinau.mahasiswaappabika.model.DataItem
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Abika Chairul Yusri
 * on Thursday, 04 June 2020
 * Bismillahirrahmanirrahim
 */
class MahasiswaAdapter(val data: List<DataItem>?, val itemClick: OnClickListener) :
    RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp

        holder.itemView.setOnClickListener {
//            val intent = Intent(context, InputActivity::class.java)
//            intent.putExtra("Data", item)
//            context.startActivity(intent)
            itemClick.detail(item)
            Toast.makeText(context, "Anda menekan ${data?.get(position)?.mahasiswaNama}", Toast.LENGTH_SHORT).show()
        }

        holder.hapus.setOnClickListener {
            itemClick.hapus(item)
        }
    }

    class MahasiswaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.tvItemNama
        val nohp = view.tvItemNo
        val hapus = view.btnHapus
    }

    interface OnClickListener {
        fun detail(item: DataItem?)
        fun hapus(item: DataItem?)
    }
}