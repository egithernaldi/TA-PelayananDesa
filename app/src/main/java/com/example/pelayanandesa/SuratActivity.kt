package com.example.smartvillage.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.pelayanandesa.R
import com.example.pelayanandesa.activity.*
import com.example.pelayanandesa.helper.SharedPref

class SuratActivity : Fragment() {

    lateinit var s: SharedPref

    lateinit var domisili: CardView
    lateinit var kematian: CardView
    lateinit var sku: CardView
    lateinit var kelahiran: CardView
    lateinit var belumnikah: CardView
    lateinit var keramaian: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_surat_activity, container, false)
        init(view)

        s = SharedPref(requireActivity())

        mainButton()
        return view

    }

    private fun mainButton(){
        domisili.setOnClickListener {
            startActivity(Intent(requireActivity(), DomisiliActivity::class.java))
        }
        sku.setOnClickListener {
            startActivity(Intent(requireActivity(), SkuActivity::class.java))
        }
        belumnikah.setOnClickListener {
            startActivity(Intent(requireActivity(), BlmNikahActivity::class.java))
        }
        keramaian.setOnClickListener {
            startActivity(Intent(requireActivity(), KeramaianActivity::class.java))
        }

    }

    private fun init(view: View){
        domisili = view.findViewById(R.id.domisili)
        sku = view.findViewById(R.id.sku)
        belumnikah = view.findViewById(R.id.belumnikah)
        keramaian = view.findViewById(R.id.keramaian)
    }



}