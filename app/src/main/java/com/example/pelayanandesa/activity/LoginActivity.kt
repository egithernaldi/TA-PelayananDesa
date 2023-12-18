package com.example.pelayanandesa.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivityLoginBinding
import com.example.pelayanandesa.helper.SharedPref
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding // Update the binding declaration
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) // Use ViewBinding to inflate the layout
        setContentView(binding.root)

        s = SharedPref(this)
        binding.signupRedirectText.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (binding.loginNik.text.isEmpty()) {
            binding.loginNik.error = "Kolom Email tidak boleh kosong"
            binding.loginNik.requestFocus()
            return
        } else if (binding.loginPassword.text.isEmpty()) {
            binding.loginPassword.error = "Kolom Password tidak boleh kosong"
            binding.loginPassword.requestFocus()
            return
        }

        binding.pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(
            binding.loginNik.text.toString(),
            binding.loginPassword.text.toString(),
        ).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                binding.pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                binding.pb.visibility = View.GONE
                val respon = response.body()!!
                if (respon.status == 200) {
                    s.setStatusLogin(true)
                    s.setString(s.nik, respon.data.nik)
                    s.setString(s.email, respon.data.email)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Selamat datang " + respon.data.nama, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Error:" + respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}



