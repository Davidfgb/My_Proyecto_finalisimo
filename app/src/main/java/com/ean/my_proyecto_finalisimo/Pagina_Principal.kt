package com.ean.my_proyecto_finalisimo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Pagina_Principal : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_principal)


        val boton_visualizar_desechos = findViewById<Button>(R.id.bn_pg_vizual_desechos)
        boton_visualizar_desechos.setOnClickListener {
            val intent = Intent(this,Visualizar_Desechos::class.java)
            startActivity(intent)
        }
        val boton_ingresar_Metas = findViewById<Button>(R.id.bn_pg_metas)
        boton_ingresar_Metas.setOnClickListener {
            val intent = Intent(this,Metas_desechos::class.java)
            startActivity(intent)
        }
        val boton_actualizar_desechos = findViewById<Button>(R.id.bn_pg_actualizar_desechos)
        boton_actualizar_desechos.setOnClickListener {
            val intent = Intent(this,Actualizar_desechos::class.java)
            startActivity(intent)
        }
        val boton_eliminar_desechos = findViewById<Button>(R.id.bn_pg_eliminar_desechos)
        boton_eliminar_desechos.setOnClickListener {
            val intent = Intent(this,Eliminar_Desechos::class.java)
            startActivity(intent)
        }
        val boton_ingresar_info = findViewById<Button>(R.id.bn_pg_ingreso_info)
        boton_ingresar_info.setOnClickListener {
            val intent = Intent(this,Ingreso_Info::class.java)
            startActivity(intent)
        }


    }
}