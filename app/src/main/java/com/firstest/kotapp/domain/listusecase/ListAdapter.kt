package com.firstest.kotapp.domain.listusecase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firstest.kotapp.R
import com.firstest.kotapp.domain.entity.DnDSpell
import com.squareup.picasso.Picasso
import java.util.*

class ListAdapter(
    myDataset: List<DnDSpell>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    private val values = myDataset
    private val imaurl = "https://cdn.sstatic.net/Sites/rpg/Img/apple-touch-icon@2.png?v=4c03147b9ffe"
    class ViewHolder(
        layout: View
    ): RecyclerView.ViewHolder(layout) {
        var txtHeader: TextView = layout.findViewById<View>(R.id.firstLine) as TextView
        var icon = itemView.findViewById(R.id.icon) as ImageView

    }
    @NonNull
    @Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetText")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spell: DnDSpell = values?.get(position)
        if (spell != null){
            holder.txtHeader.text = spell.name
            Picasso.get().load(imaurl).resize(175, 215).into(holder.icon)
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}