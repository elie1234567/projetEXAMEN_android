package com.example.examenandroide

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RemoteViews.RemoteCollectionItems
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class elie :RecyclerView.Adapter<elie.viewHolder>(){
    private var stdlist:ArrayList<crudclass> = ArrayList()
    private var onClickItem:((crudclass) -> Unit)? = null
    @SuppressLint("SuspiciousIndentation")
    fun addItems(items: ArrayList<crudclass>){
      this.stdlist=items
        notifyDataSetChanged()
    }
    fun setOneClickItems(callback: (crudclass)->Unit){
        this.onClickItem=callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.yewa,parent,false)

    )

    override fun getItemCount(): Int {
      return stdlist.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val s=stdlist[position]
        holder.aseho(s)
        holder.itemView.setOnClickListener{
            onClickItem?.invoke(s)
        }
    }
    class viewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id=view.findViewById<TextView>(R.id.tvid)
        private var name=view.findViewById<TextView>(R.id.tvDate)
        private var sex=view.findViewById<TextView>(R.id.tvGenre)
        private var date=view.findViewById<TextView>(R.id.tvDate)
        private var age=view.findViewById<TextView>(R.id.tvAge)
        private var btdelete=view.findViewById<Button>(R.id.btndelete)
        fun aseho(std:crudclass){
            id.text=std.id.toString()
            name.text=std.Nom
            sex.text=std.Genre
            date.text=std.Datenss
            age.text=std.Age


        }

    }

}