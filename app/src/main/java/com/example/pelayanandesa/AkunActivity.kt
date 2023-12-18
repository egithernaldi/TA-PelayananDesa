package com.example.pelayanandesa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Toast
import com.example.pelayanandesa.activity.LoginActivity
import com.example.pelayanandesa.app.ApiConfig
import com.example.pelayanandesa.databinding.ActivitySignupBinding
import com.example.pelayanandesa.databinding.FragmentAkunActivityBinding
import com.example.pelayanandesa.helper.SharedPref
import com.example.pelayanandesa.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 // ... (imports)

    class AkunActivity : Fragment() {
        private lateinit var binding: FragmentAkunActivityBinding
        private lateinit var s: SharedPref


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentAkunActivityBinding.inflate(inflater, container, false)
            return binding.root
            setData()
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            s = SharedPref(requireActivity())

            binding.perbarui.setOnClickListener {
                updateProfil()
            }
            binding.btnLogout.setOnClickListener {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                requireActivity().finish()
            }
        }

        fun setData() {
            binding.tvNama.text = s.getString(s.nama)
            binding.tvEmail.text = s.getString(s.email)
//        tvPhone.text = user.phone
        }
        private fun updateProfil() {
            if (!validateInputs()) {
                return
            }

            binding.pb.visibility = View.VISIBLE
            ApiConfig.instanceRetrofit.updateProfile(
                binding.nik.text.toString(),
                binding.nama.text.toString(),
                binding.tempatLahir.text.toString(),
                binding.tanggalLahir.text.toString(),
                binding.pekerjaan.text.toString(),
                binding.jenisKelamin.text.toString(),
                binding.agama.text.toString(),
                binding.alamat.text.toString(),
                binding.noHp.text.toString()
            ).enqueue(object : Callback<ResponModel> {
                override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                    binding.pb.visibility = View.GONE
                    if (response.isSuccessful) {
                        // Handle success
                        Toast.makeText(requireContext(), "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle API error
                        Toast.makeText(requireContext(), "Gagal memperbarui profil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                    // Handle network failure
                    binding.pb.visibility = View.GONE
                    Toast.makeText(requireContext(), "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show()
                }
            })
        }

        private fun validateInputs(): Boolean {
            val fields = arrayOf(
                binding.nik,
                binding.nama,
                binding.tempatLahir,
                binding.tanggalLahir,
                binding.pekerjaan,
                binding.jenisKelamin,
                binding.agama,
                binding.alamat,
                binding.noHp
            )

            for (field in fields) {
                if (field.text.isEmpty()) {
                    field.error = "Kolom ${field.hint} tidak boleh kosong"
                    field.requestFocus()
                    return false
                }
            }
            return true
        }
    }
