package com.example.pelayanandesa.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.R
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivityKeramaianBinding
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeramaianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeramaianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeramaianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            kirim()
        }
    }

    private fun kirim() {
        with(binding) {
            if (edtNikPanitia.text.isEmpty()) {
                edtNikPanitia.error = "Kolom NIK tidak boleh kosong"
                edtNikPanitia.requestFocus()
                return
            } else if (edtNamaPanitia.text.isEmpty()) {
                edtNamaPanitia.error = "Kolom Nama tidak boleh kosong"
                edtNamaPanitia.requestFocus()
                return
            } else if (edtUmurPanitia.text.isEmpty()) {
                edtUmurPanitia.error = "Kolom Umur tidak boleh kosong"
                edtUmurPanitia.requestFocus()
                return
            } else if (edtPekerjaanPanitia.text.isEmpty()) {
                edtPekerjaanPanitia.error = "Kolom Pekerjaan tidak boleh kosong"
                edtPekerjaanPanitia.requestFocus()
                return
            } else if (edtAlamatPanitia.text.isEmpty()) {
                edtAlamatPanitia.error = "Kolom Alamat tidak boleh kosong"
                edtAlamatPanitia.requestFocus()
                return
            } else if (edtTglKegiatan.text.isEmpty()) {
                edtTglKegiatan.error = "Kolom Tanggal Kegiatan tidak boleh kosong"
                edtTglKegiatan.requestFocus()
                return
            } else if (edtTempatKegiatan.text.isEmpty()) {
                edtTempatKegiatan.error = "Kolom Tempat Kegiatan tidak boleh kosong"
                edtTempatKegiatan.requestFocus()
                return
            } else if (edtKegiatan.text.isEmpty()) {
                edtKegiatan.error = "Kolom Kegiatan tidak boleh kosong"
                edtKegiatan.requestFocus()
                return
            }
        }

        binding.pbkeramaian.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.keramaian(
            binding.edtNikPanitia.text.toString(),
            binding.edtNamaPanitia.text.toString(),
            binding.edtUmurPanitia.text.toString(),
            binding.edtPekerjaanPanitia.text.toString(),
            binding.edtAlamatPanitia.text.toString(),
            binding.edtTglKegiatan.text.toString(),
            binding.edtTempatKegiatan.text.toString(),
            binding.edtKegiatan.text.toString()
        ).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle Gagal
                binding.pbkeramaian.visibility = View.GONE
                Toast.makeText(this@KeramaianActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle Berhasil
                binding.pbkeramaian.visibility = View.GONE
                val respon = response.body()!!
                if (respon.status ==  200) {
                    val intent = Intent(this@KeramaianActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@KeramaianActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
