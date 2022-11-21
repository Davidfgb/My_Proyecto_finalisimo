package com.ean.my_proyecto_finalisimo


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.common.subtyping.qual.Bottom
import java.text.SimpleDateFormat
import java.util.*

class Visualizar_Desechos : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_desechos)


        val fecha_act = SimpleDateFormat("yyyy")
        val fecha_a = fecha_act.format(Date())
        val fecha_actual = fecha_a.toInt()

        val mes_act = SimpleDateFormat("M")
        val mes_a= mes_act.format(Date())
        val mes_actual = mes_a.toInt()



        val gmail = findViewById<TextView>(R.id.editText_visu_gmail)
        val boton_mostrar = findViewById<Button>(R.id.bn_visu_mostrar)
        val volver = findViewById<Button>(R.id.bn_visu_volver)
        val gmail_id = gmail.text.toString()


        val mostrar_datos = findViewById<TextView>(R.id.text_visu_motrar_datos)

        boton_mostrar.setOnClickListener {
            try {
                // db.collection("desechos").document(id_gmail).collection(id_fecha).document(id_mes).set(desechos)
                val docRef = db.collection("desechos").document(gmail.text.toString()).collection(fecha_actual.toString()).document(mes_actual.toString())
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                            mostrar_datos.setText("${document.data}")
                        } else {
                            Log.d(ContentValues.TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "get failed with ", exception)
                    }
            }catch (e:Exception){
                Toast.makeText(baseContext, "No se pudo mostrar datos", Toast.LENGTH_SHORT).show()
            }
        }
        volver.setOnClickListener {
            val intent = Intent(this,Pagina_Principal::class.java)
            startActivity(intent)
        }

    }




}

