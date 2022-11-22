package com.ean.my_proyecto_finalisimo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Actualizar_desechos : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_desechos)

        auth = Firebase.auth

        val fecha_act = SimpleDateFormat("yyyy")
        val fecha_a = fecha_act.format(Date())
        val fecha_actual = fecha_a.toString()

        val mes_act = SimpleDateFormat("M")
        val mes_a= mes_act.format(Date())
        val mes_actual = mes_a.toString()

        val boton_actualizar = findViewById<Button>(R.id.bn_actuali_info_actuali)
        val boton_volver = findViewById<Button>(R.id.bn_acutuali_info_volver)
        val correo = findViewById<EditText>(R.id.editText_actuli_info_correo)
        val clave = findViewById<EditText>(R.id.editText_actuali_info_contraseña)
        val fecha = findViewById<EditText>(R.id.editText_actuali_info_año)
        val mes = findViewById<EditText>(R.id.editText_actuali_info_mes)
        val inertes = findViewById<EditText>(R.id.editTex_actuali_info_inertes)
        val urbanos = findViewById<EditText>(R.id.editTex_actuali_info_urbanos)
        val peligrosos = findViewById<EditText>(R.id.editTex_actuali_info_peligrosos)
        val otros = findViewById<EditText>(R.id.editTex_actuali_info_otros)


        boton_actualizar.setOnClickListener {
            val correo_st = correo.text.toString().lowercase()
            val clave_st = clave.text.toString()
            val fecha_st = fecha.text.toString()
            val mes_st = mes.text.toString()
            val inertes_st = inertes.text.toString()
            val urbanos_st = urbanos.text.toString()
            val peligrosos_st = peligrosos.text.toString()
            val otros_st = otros.text.toString()

            try {
                if (correo_st.isEmpty() || clave_st.isEmpty() || fecha_st.isEmpty() || mes_st.isEmpty()) {
                    throw Exception("Los campos no pueden estar Vacios!!")
                } else {
                    if(fecha_st.toInt() > fecha_actual.toInt() || mes_st.toInt() > mes_actual.toInt()){
                        throw Exception("La fecha no puede ser mayor a la actual!!")
                    }else {
                        auth.signInWithEmailAndPassword(correo_st, clave_st)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        baseContext,
                                        "Informacion Actaulizada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(ContentValues.TAG, "signInWithCustomToken:success")
                                    //updateUI(user)
                                    val desechos = hashMapOf(
                                        //mes_desecho to desechos_list,
                                        "desechos_inertes" to inertes_st.toInt(),
                                        "desechos_urbanos" to urbanos_st.toInt(),
                                        "desechos_peligrosos" to peligrosos_st.toInt(),
                                        "desechos_otros" to otros_st.toInt(),
                                    )
                                    db.collection("desechos").document(correo.text.toString())
                                        .collection(fecha.text.toString())
                                        .document(mes.text.toString())
                                        .set(desechos)
                                        .addOnSuccessListener {
                                            Log.d(
                                                ContentValues.TAG,
                                                "DocumentSnapshot successfully written!"
                                            )
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(ContentValues.TAG, "Error writing document", e)
                                        }
                                } else {
                                    Toast.makeText(
                                        baseContext, "usuario no registrado",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                }
            }
            catch (e:Exception){
                Toast.makeText(baseContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        boton_volver.setOnClickListener {
            val intent = Intent(this,Pagina_Principal::class.java)
            startActivity(intent)
        }


    }
}