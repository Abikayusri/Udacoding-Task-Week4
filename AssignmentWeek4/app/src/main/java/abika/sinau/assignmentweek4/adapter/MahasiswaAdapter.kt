package abika.sinau.assignmentweek4.adapter

import abika.sinau.assignmentweek4.R
import abika.sinau.assignmentweek4.model.DataItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Abika Chairul Yusri
 * on Thursday, 04 June 2020
 * Bismillahirrahmanirrahim
 */
class MahasiswaAdapter(val data: List<DataItem>?) :
    RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat
    }

    class MahasiswaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.tvItemNama
        val nohp = view.tvItemNo
        val alamat = view.tvItemAlamat
    }
}