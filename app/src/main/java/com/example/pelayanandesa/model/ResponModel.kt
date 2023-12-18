package com.example.pelayanandesa.model

import com.google.gson.annotations.SerializedName


class ResponModel {
    var status: Int = 0
    var data: User = User()
    lateinit var message: String

}

