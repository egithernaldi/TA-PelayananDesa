package com.example.pelayanandesa.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pelayanandesa.MainActivity
import com.example.pelayanandesa.R
import com.example.pelayanandesa.databinding.ActivityDomisiliBinding
import com.example.pelayanandesa.model.ResponModel
import com.example.pelayanandesa.app.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DomisiliActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDomisiliBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomisiliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            kirim()
        }
    }

    private fun kirim() {
        with(binding) {
            if (edtNik.text.isEmpty()) {
                edtNik.error = "Kolom nik tidak boleh kosong"
                edtNik.requestFocus()
                return
            } else if (edtNama.text.isEmpty()) {
                edtNama.error = "Kolom nama tidak boleh kosong"
                edtNama.requestFocus()
                return
            } else if (edtJk.text.isEmpty()) {
                edtJk.error = "Kolom jenis kelamin tidak boleh kosong"
                edtJk.requestFocus()
                return
            } else if (edtTempatlahir.text.isEmpty()) {
                edtTempatlahir.error = "Kolom tempat lahir tidak boleh kosong"
                edtTempatlahir.requestFocus()
                return
            } else if (edtTanggallahir.text.isEmpty()) {
                edtTanggallahir.error = "Kolom tanggal lahir tidak boleh kosong"
                edtTanggallahir.requestFocus()
                return
            } else if (edtWarga.text.isEmpty()) {
                edtWarga.error = "Kolom kewarganegaraan tidak boleh kosong"
                edtWarga.requestFocus()
                return
            } else if (edtAgama.text.isEmpty()) {
                edtAgama.error = "Kolom agama tidak boleh kosong"
                edtAgama.requestFocus()
                return
            } else if (edtPekerjaan.text.isEmpty()) {
                edtPekerjaan.error = "Kolom pekerjaan tidak boleh kosong"
                edtPekerjaan.requestFocus()
                return
            } else if (edtAlamat.text.isEmpty()) {
                edtAlamat.error = "Kolom alamat tidak boleh kosong"
                edtAlamat.requestFocus()
                return
            }
        }

        binding.pbdomisili.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.domisili(
            binding.edtNik.text.toString(),
            binding.edtNama.text.toString(),
            binding.edtJk.text.toString(),
            binding.edtTempatlahir.text.toString(),
            binding.edtTanggallahir.text.toString(),
            binding.edtWarga.text.toString(),
            binding.edtAgama.text.toString(),
            binding.edtPekerjaan.text.toString(),
            binding.edtAlamat.text.toString()
        ).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handle gagal
                binding.pbdomisili.visibility = View.GONE
                Toast.makeText(this@DomisiliActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handle success
                binding.pbdomisili.visibility = View.GONE
                val respon = response.body()!!
                if (respon.status ==  200) {
                    val intent = Intent(this@DomisiliActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@DomisiliActivity, "Data Berhasil Dikirimkan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
