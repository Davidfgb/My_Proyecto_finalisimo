package com.ean.my_proyecto_finalisimo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Metas_desechos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas_desechos)

        val db = Firebase.firestore



        val boton_registar = findViewById<Button>(R.id.bn_mta_registrar)
        val boton_volver = findViewById<Button>(R.id.bn_mta_volver)
        val correo = findViewById<TextView>(R.id.editText_mta_correo)
        val inertes = findViewById<TextView>(R.id.editText_mta_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_mta_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_mta_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_mta_otros)



        val sdf = SimpleDateFormat("yyyy")
        val fecha = sdf.format(Date())

        val mes = SimpleDateFormat("M")
        val mes_st = mes.format(Date())


        boton_registar.setOnClickListener {
            try {

                val correo_st = correo.text.toString()
                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()

                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty() || correo_st.isEmpty()) {
                    Toast.makeText(baseContext, "Campos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    val metas = hashMapOf(
                        "inertes" to desecho_inertes.toInt(),
                        "urbanos" to desecho_urbanos.toInt(),
                        "peligrosos" to desecho_peligrosos.toInt(),
                        "otros" to desecho_otros.toInt()
                    )
                    db.collection("Metas").document(correo.text.toString()).collection(fecha.toString()).document(mes_st.toString()).set(metas)
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully written!"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error writing document",
                                e
                            )
                        }
                    Toast.makeText(baseContext, "Informacion Registarda", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {

            }
        }
        boton_volver.setOnClickListener {
            val intent = Intent(this, Pagina_Principal::class.java)
            startActivity(intent)
        }





    }
}