package com.example.examenandroide

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.database.getIntOrNull

class SQlite(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "crud.db"
        private const val TBL_CRUD = "dbl_crud"
        private const val ID = "id"
        private const val NOM = "Nom"
        private const val genre = "Genre"
        private const val datenss = "Datenss"
        private const val age = "Age"


    }
     fun updata(sdt: crudclass):Int{
        val db=this.writableDatabase
        val conte=ContentValues()
        conte.put(ID,sdt.id)
        conte.put(NOM,sdt.Nom)
        conte.put(genre,sdt.Genre)
        conte.put(datenss,sdt.Datenss)
        conte.put(age,sdt.Age)
        val succ=db.update(TBL_CRUD,conte,"id=" + sdt.id,null)
        db.close()
        return succ
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableCrud = ("CREATE TABLE " + TBL_CRUD + "(" +
                "$ID INTEGER PRIMARY KEY, $NOM TEXT, $genre TEXT, $datenss TEXT, $age TEXT)")
        db?.execSQL(createTableCrud)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CRUD")
        onCreate(db)
    }

    fun insertCrud(std: crudclass): Long {
        try {
            val db = this.writableDatabase
            val comme = ContentValues()
            // Assurez-vous que les noms de colonnes correspondent à votre table
            comme.put(ID, std.id)
            comme.put(NOM, std.Nom)
            comme.put(genre, std.Genre)
            comme.put(datenss, std.Datenss)
            comme.put(age, std.Age)

            // Utilisez null comme nullColumnHack
            val elie = db.insert(TBL_CRUD, null, comme)
            // Ne fermez pas la base de données ici

            Log.d("InsertCrud", "Insertion successful")


            return elie
        } catch (e: Exception) {
            // Ajoutez un log ici
            Log.e("InsertError", "Error during insertion", e)
            return -1
        }
    }



    @SuppressLint("Range")
    fun allcrud(): ArrayList<crudclass> {
        val c: ArrayList<crudclass> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CRUD"
        val db = this.readableDatabase
        var curser: Cursor? = null

        try {
            curser = db.rawQuery(selectQuery, null)

            if (curser != null) {
                if (curser.moveToFirst()) {
                    do {
                        val id = curser.getInt(curser.getColumnIndex("`$ID`"))
                        val nom = curser.getString(curser.getColumnIndex("`$NOM`"))
                        val genre = curser.getString(curser.getColumnIndex("`$genre`"))
                        val datenss = curser.getString(curser.getColumnIndex("`$datenss`"))
                        val age = curser.getString(curser.getColumnIndex("`$age`"))

                        val std = crudclass(id = id, Nom = nom, Genre =genre, Datenss = datenss, Age =age )
                        c.add(std)

                    } while (curser.moveToNext())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            curser?.close()
            db.close()
        }

        return c
    }


}

//
//




