package com.example.pelayanandesa.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivitySignupBinding
import com.example.pelayanandesa.helper.SharedPref
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)

        binding.daftar.setOnClickListener {
            register()
        }
        binding.loginRedirectText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    fun register() {
        if (binding.nik.text.isEmpty()) {
            binding.nik.error = "Kolom Nama tidak boleh kosong"
            binding.nik.requestFocus()
            return
        } else if (binding.nama.text.isEmpty()) {
            binding.nama.error = "Kolom Email tidak boleh kosong"
            binding.nama.requestFocus()
            return
        } else if (binding.email.text.isEmpty()) {
            binding.email.error = "Kolom Nomor Telepon tidak boleh kosong"
            binding.email.requestFocus()
            return
        } else if (binding.signupPassword.text.isEmpty()) {
            binding.signupPassword.error = "Kolom Password tidak boleh kosong"
            binding.signupPassword.requestFocus()
            return
        } else if (binding.tempatLahir.text.isEmpty()) {
            binding.tempatLahir.error = "Kolom Email tidak boleh kosong"
            binding.tempatLahir.requestFocus()
            return
        } else if (binding.tanggalLahir.text.isEmpty()) {
            binding.tanggalLahir.error = "Kolom Nomor Telepon tidak boleh kosong"
            binding.tanggalLahir.requestFocus()
            return
        } else if (binding.telepon.text.isEmpty()) {
            binding.telepon.error = "Kolom Password tidak boleh kosong"
            binding.telepon.requestFocus()
            return
        }

        binding.pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(
            binding.nik.text.toString(),
            binding.nama.text.toString(),
            binding.email.text.toString(),
            binding.signupPassword.text.toString(),
            binding.tempatLahir.text.toString(),
            binding.tanggalLahir.text.toString(),
            binding.telepon.text.toString()
        ).enqueue(object : Callback<ResponModel> {

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                binding.pb.visibility = View.GONE
                Toast.makeText(this@SignupActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                binding.pb.visibility = View.GONE
                val respon = response.body()
                if (respon != null) {
                    s.setStatusLogin(true)
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@SignupActivity, "Selamat datang ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignupActivity, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
