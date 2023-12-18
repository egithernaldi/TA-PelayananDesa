package com.example.pelayanandesa.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.R
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivitySkuBinding
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkuActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySkuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            kirim()
        }
    }

    private fun kirim() {
        with(binding) {
            if (edtNik.text.isEmpty()) {
                edtNik.error = "Kolom NIK tidak boleh kosong"
                edtNik.requestFocus()
                return
            } else if (edtNama.text.isEmpty()) {
                edtNama.error = "Kolom Nama tidak boleh kosong"
                edtNama.requestFocus()
                return
            } else if (edtUmur.text.isEmpty()) {
                edtUmur.error = "Kolom Umur tidak boleh kosong"
                edtUmur.requestFocus()
                return
            } else if (edtAlamat.text.isEmpty()) {
                edtAlamat.error = "Kolom Alamat tidak boleh kosong"
                edtAlamat.requestFocus()
                return
            } else if (edtNohp.text.isEmpty()) {
                edtNohp.error = "Kolom No HP tidak boleh kosong"
                edtNohp.requestFocus()
                return
            } else if (edtNamaUsaha.text.isEmpty()) {
                edtNamaUsaha.error = "Kolom Nama Usaha tidak boleh kosong"
                edtNamaUsaha.requestFocus()
                return
            }
        }

        binding.pbsku.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.sku(
            binding.edtNik.text.toString(),
            binding.edtNama.text.toString(),
            binding.edtUmur.text.toString(),
            binding.edtAlamat.text.toString(),
            binding.edtNohp.text.toString(),
            binding.edtNamaUsaha.text.toString()
        ).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle Gagal
                binding.pbsku.visibility = View.GONE
                Toast.makeText(this@SkuActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle Berhasil
                binding.pbsku.visibility = View.GONE
                val respon = response.body()!!
                if (respon.status == 200) {
                    val intent = Intent(this@SkuActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@SkuActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
