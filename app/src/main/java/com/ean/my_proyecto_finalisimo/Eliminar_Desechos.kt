package com.ean.my_proyecto_finalisimo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Eliminar_Desechos : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_desechos)

        auth = Firebase.auth

        val correo = findViewById<EditText>(R.id.editText_elimi_correo)
        val clave = findViewById<EditText>(R.id.editText_elimi_clave)
        val fecha = findViewById<EditText>(R.id.editText_elimi_año)
        val mes = findViewById<EditText>(R.id.editText_elimi_mes)
        val boton_eliminar = findViewById<Button>(R.id.bn_elimi_elimnar)
        val boton_volver = findViewById<Button>(R.id.bn_elimi_volver)

        boton_eliminar.setOnClickListener {
            try {
                val correo_st = correo.text.toString()
                val clave_st = clave.text.toString()
                val fecha_st = fecha.text.toString()
                val mes_st = mes.text.toString()

                if (correo_st.isEmpty() || clave_st.isEmpty() || fecha_st.isEmpty() || mes_st.isEmpty()) {
                    throw Exception("Los campos no pueden estar Vacios!!")
                } else {
                    auth.signInWithEmailAndPassword(correo_st, clave_st)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                db.collection("desechos").document(correo.text.toString()).collection(fecha.text.toString()).document(mes.text.toString()).delete()
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            baseContext,
                                            "Informacion Eliminada",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            } else {
                                Toast.makeText(
                                    baseContext,
                                    "Error al eliminar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } catch (e: Exception) {
                Toast.makeText(baseContext, "Error al Eliminar", Toast.LENGTH_SHORT).show()
            }
        }
        boton_volver.setOnClickListener {
            val intent = Intent(this, Pagina_Principal::class.java)
            startActivity(intent)
        }
    }
}