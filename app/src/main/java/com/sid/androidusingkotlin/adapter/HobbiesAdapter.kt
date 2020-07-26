package com.sid.androidusingkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.androidusingkotlin.model.Hobby
import com.sid.androidusingkotlin.R
import com.sid.androidusingkotlin.showToast
import kotlinx.android.synthetic.main.list_item.view.*

class HobbiesAdapter(val context:Context,private val hobbies:List<Hobby>): RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby = hobbies[position]
        holder.setData(hobby,position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var currentItem: Hobby? = null
        var currentPosition: Int = 0;

            fun setData(hobby: Hobby?, pos: Int){
                hobby?.let {
                    itemView.tvTitle.text = hobby.title
                    currentItem = hobby
                    currentPosition = pos
                }
            }

        init {
            itemView.setOnClickListener {
                context.showToast("Clicked: "+currentItem!!.title+", Position: "+currentPosition)
            }
        }
    }
}