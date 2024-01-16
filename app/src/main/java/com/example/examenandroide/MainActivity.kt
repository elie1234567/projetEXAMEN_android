package com.example.examenandroide

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var edname:EditText
    private lateinit var sex:EditText
    private lateinit var date:EditText
    private lateinit var age:TextView
    private lateinit var bt:Button
    private lateinit var bv:Button
    private lateinit var update:Button
    private lateinit var sqliteHelper:SQlite
    private lateinit var recycleview: RecyclerView
    private  var adapter:elie?=null
    private  var std:crudclass?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView();
        recycle();
        sqliteHelper= SQlite(this)
        bt.setOnClickListener{
            add();

        }
        bv.setOnClickListener{
            getview();

        }
        update.setOnClickListener{
           updatefu()
        }
        adapter?.setOneClickItems { Toast.makeText(this,it.Nom,Toast.LENGTH_SHORT).show()
        edname.setText(it.Nom)
            sex.setText(it.Nom)
            date.setText(it.Nom)
            age.setText(it.Nom)

           std=it


        }

    }

    private fun updatefu() {
        val name=edname.text.toString()
        val sex=sex.text.toString()
        val date=date.text.toString()
        val age=age.text.toString()
       if(name == std?.Nom && sex == std?.Genre && date == std?.Datenss&& age == std?.Age){
           Toast.makeText(this,"erreur reccord",Toast.LENGTH_SHORT).show()
           return
       }
        if(std == null)return
            val std=crudclass(id=std!!.id, Nom=name , Genre = sex, Datenss = date, Age = age)
         val status=sqliteHelper.updata(std)
          if (status > -1){
              clearEditText();
          }else{
              Toast.makeText(this,"error update",Toast.LENGTH_SHORT).show()
          }


    }


    private fun getview() {
        val stslist=sqliteHelper.allcrud()
        Log.e("pppp","${stslist.size}")
        adapter?.addItems(stslist)
    }

    private fun add() {
        val nom = edname.text.toString()
        val sex = sex.text.toString()
        val date = date.text.toString()
        val age = age.text.toString()
        if (nom.isEmpty() || sex.isEmpty()) {
            Toast.makeText(this, "Please enter text in all fields", Toast.LENGTH_SHORT).show()
            getview();

            } else {
            val std = crudclass(id = crudclass.getUniqueID(), Nom = nom, Genre = sex, Datenss = date, Age = age)

            Log.d("Insertion", "Before insertCrud")
            val status = sqliteHelper.insertCrud(std)
            Log.d("Insertion", "After insertCrud, status: $status")
            Toast.makeText(this, "Add successful", Toast.LENGTH_SHORT).show()
            clearEditText()

        }
        }




    private fun clearEditText(){
        edname.setText("")
        sex.setText("")
        age.setText("")
        date.setText("")
        edname.requestFocus()

    }
    private fun recycle(){
        recycleview.layoutManager=LinearLayoutManager(this)
        adapter=elie()
        recycleview.adapter=adapter

    }


    private fun initView() {
        edname=findViewById(R.id.edName)
        sex=findViewById(R.id.edGenre)
        date=findViewById(R.id.edDate)
        age=findViewById(R.id.textView2)
        bt=findViewById(R.id.button)
        bv=findViewById(R.id.button2)
        update=findViewById(R.id.btnUp)
        recycleview=findViewById(R.id.recyclaView)

    }

}