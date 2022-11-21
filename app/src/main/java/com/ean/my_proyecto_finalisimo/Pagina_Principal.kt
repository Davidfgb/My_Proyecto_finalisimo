package com.ean.my_proyecto_finalisimo


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Pagina_Principal : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_principal)


        /*val bundle = intent.extras
        val id_correo = bundle?.getString("correo")
        val id = id_correo.toString()

        val citiesRef = db.collection("usuario")*/

// Create a query against the collection.
        /*val query = citiesRef.whereEqualTo(id, "$id").toString()


        Toast.makeText(baseContext,query , Toast.LENGTH_SHORT).show()


        val docRef = db.collection("usuario").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    val id_gmail = document.data
                    val id_final = id_gmail.toString()
                    //Toast.makeText(baseContext,id_final , Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }*/



        val boton_visualizar_desechos = findViewById<Button>(R.id.bn_pg_vizual_desechos)
        boton_visualizar_desechos.setOnClickListener {
            val intent = Intent(this,Visualizar_Desechos::class.java)
            // intent.putExtra("correo", correo)
            startActivity(intent)
        }
        val boton_ingresar_Metas = findViewById<Button>(R.id.bn_pg_metas)
        boton_ingresar_Metas.setOnClickListener {
            val intent = Intent(this,Metas_desechos::class.java)
            //intent.putExtra("correo", correo)
            startActivity(intent)
        }
        val boton_ingresar_info = findViewById<Button>(R.id.bn_pg_ingreso_info)
        boton_ingresar_info.setOnClickListener {
            val intent = Intent(this,Ingreso_Info::class.java)
            //intent.putExtra("correo", correo)
            startActivity(intent)
        }


    }
}