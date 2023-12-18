package com.example.pelayanandesa.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.R
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivityBlmNikahBinding
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlmNikahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlmNikahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlmNikahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            kirim()
        }
    }

    private fun kirim() {
        with(binding) {
            if (edtNikBlmnikah.text.isEmpty()) {
                edtNikBlmnikah.error = "Kolom NIK tidak boleh kosong"
                edtNikBlmnikah.requestFocus()
                return
            } else if (edtNamaBlmnikah.text.isEmpty()) {
                edtNamaBlmnikah.error = "Kolom Nama tidak boleh kosong"
                edtNamaBlmnikah.requestFocus()
                return
            } else if (edtTempatlahirBlmnikah.text.isEmpty()) {
                edtTempatlahirBlmnikah.error = "Kolom Tempat Lahir tidak boleh kosong"
                edtTempatlahirBlmnikah.requestFocus()
                return
            } else if (edtTanggalLahirBlmnikah.text.isEmpty()) {
                edtTanggalLahirBlmnikah.error = "Kolom Tanggal Lahir tidak boleh kosong"
                edtTanggalLahirBlmnikah.requestFocus()
                return
            } else if (edtJkBlmnikah.text.isEmpty()) {
                edtJkBlmnikah.error = "Kolom Jenis Kelamin tidak boleh kosong"
                edtJkBlmnikah.requestFocus()
                return
            } else if (edtAgamaBlmnikah.text.isEmpty()) {
                edtAgamaBlmnikah.error = "Kolom Agama tidak boleh kosong"
                edtAgamaBlmnikah.requestFocus()
                return
            } else if (edtPekerjaanBlmnikah.text.isEmpty()) {
                edtPekerjaanBlmnikah.error = "Kolom Pekerjaan tidak boleh kosong"
                edtPekerjaanBlmnikah.requestFocus()
                return
            } else if (edtAlamatBlmnikah.text.isEmpty()) {
                edtAlamatBlmnikah.error = "Kolom Alamat tidak boleh kosong"
                edtAlamatBlmnikah.requestFocus()
                return
            }
        }

        binding.pbblmnikah.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.blmnikah(
            binding.edtNikBlmnikah.text.toString(),
            binding.edtNamaBlmnikah.text.toString(),
            binding.edtTempatlahirBlmnikah.text.toString(),
            binding.edtTanggalLahirBlmnikah.text.toString(),
            binding.edtJkBlmnikah.text.toString(),
            binding.edtAgamaBlmnikah.text.toString(),
            binding.edtPekerjaanBlmnikah.text.toString(),
            binding.edtAlamatBlmnikah.text.toString()
        ).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // Handle gagal
                binding.pbblmnikah.visibility = View.GONE
                Toast.makeText(this@BlmNikahActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // Handle berhasil
                binding.pbblmnikah.visibility = View.GONE
                val respon = response.body()!!
                if (respon.status == 200) {
                    val intent = Intent(this@BlmNikahActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@BlmNikahActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
