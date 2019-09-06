package com.example.mvpcakemodelwithretrofitandrxjava.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpcakemodelwithretrofitandrxjava.R
import com.example.mvpcakemodelwithretrofitandrxjava.common.inflate
import com.example.mvpcakemodelwithretrofitandrxjava.common.loadImage
import com.example.mvpcakemodelwithretrofitandrxjava.model.CakeModel
import kotlinx.android.synthetic.main.cardview_rows.view.*


class CakeAdapter (private val cakeModel: List<CakeModel>) : RecyclerView.Adapter<CakeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
            val view: View = parent.inflate(R.layout.cardview_rows, false)
            return CakeViewHolder(view)
        }

        override fun getItemCount(): Int {
            return cakeModel.size
        }

        override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
            holder.tvTitle.text = cakeModel[position].title
            holder.tvDescription.text = cakeModel[position].desc
            holder.imgView.loadImage(cakeModel[position].image)
        }
    }

    class CakeViewHolder (view: View): RecyclerView.ViewHolder(view){

        val tvTitle = view.tv_title
        val tvDescription = view.tv_description
        val imgView = view.img_view
}

