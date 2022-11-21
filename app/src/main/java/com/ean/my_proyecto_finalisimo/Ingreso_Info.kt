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


class Ingreso_Info : AppCompatActivity() {

    val db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_info)
        // Trae la año actual y el mes actual
        val fecha_act = SimpleDateFormat("yyyy")
        val fecha_a = fecha_act.format(Date())
        val fecha_actual = fecha_a.toString()

        val mes_act = SimpleDateFormat("M")
        val mes_a= mes_act.format(Date())
        val mes_actual = mes_a.toString()


        val gmail = findViewById<TextView>(R.id.editText_ingr_info_gmail)
        val inertes = findViewById<TextView>(R.id.editTex_ingre_info_inertes)
        val urbanos = findViewById<TextView>(R.id.editText_ingr_info_urbanos)
        val peligrosos = findViewById<TextView>(R.id.editText_ingr_info_peligrosos)
        val otros = findViewById<TextView>(R.id.editText_ingr_info_otros)
        val boton_guardar = findViewById<Button>(R.id.bn_ingr_guardar)
        val boton_volver = findViewById<Button>(R.id.bn_ingr_volver)


        val desechos_info = arrayListOf<Pair<String,Int>>()

        boton_guardar.setOnClickListener {
            try {
                var i = 0
                val id_gmail = gmail.text.toString()
                val desecho_inertes = inertes.text.toString()
                val desecho_urbanos = urbanos.text.toString()
                val desecho_peligrosos = peligrosos.text.toString()
                val desecho_otros = otros.text.toString()
                if (desecho_inertes.isEmpty() || desecho_peligrosos.isEmpty() || desecho_urbanos.isEmpty() || desecho_otros.isEmpty()) {
                    Toast.makeText(baseContext, "Campos Vacios ", Toast.LENGTH_SHORT).show()
                }else {
                    val docRef = db.collection("desechos").document(gmail.text.toString())
                        .collection(fecha_actual.toString()).document(mes_actual.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                                Toast.makeText(
                                    this,
                                    "Ya se ingreso informacion para este mes",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val desechos = hashMapOf(
                                    //mes_desecho to desechos_list,
                                    "desechos_inertes" to desecho_inertes.toInt(),
                                    "desechos_urbanos" to desecho_urbanos.toInt(),
                                    "desechos_peligrosos" to desecho_peligrosos.toInt(),
                                    "desechos_otros" to desecho_otros.toInt(),
                                )

                                db.collection("desechos").document(id_gmail).collection(fecha_actual).document(mes_actual).set(desechos)
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
                        }
                        .addOnFailureListener { exception ->
                            Log.d(ContentValues.TAG, "get failed with ", exception)
                        }


                }
            } catch (e: Exception) {

            }
        }
        boton_volver.setOnClickListener {
            val intent = Intent(this,Pagina_Principal::class.java)
            startActivity(intent)
        }
    }
}