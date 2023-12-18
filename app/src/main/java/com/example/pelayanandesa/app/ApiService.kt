package com.example.pelayanandesa.app

import com.example.pelayanandesa.model.ResponModel
import com.example.pelayanandesa.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_Lahir") tanggal_lahir: String,
        @Field("no_hp") no_hp: String
    ): Call<ResponModel>

    @GET("cekLogin")
    fun cekLogin(): Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("nik") nik: String,
        @Field("password") password: String,
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("updateProfil") // Replace with your actual updateProfile endpoint
    fun updateProfile(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("tanggal_lahir") tanggalLahir: String,
        @Field("tempat_lahir") tempatLahir: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("jenis_kelamin") jenisKelamin: String,
        @Field("agama") agama: String,
        @Field("alamat") alamat: String,
        @Field("no_hp") no_hp: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("domisili")
    fun domisili(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("kewarganegaraan") kewarganegaraan: String,
        @Field("agama") agama: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String
    ): Call<ResponModel>


    @FormUrlEncoded
    @POST("sku")
    fun sku(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("umur") umur: String,
        @Field("alamat") alamat: String,
        @Field("no_hp") no_hp: String,
        @Field("nama_usaha") nama_usaha: String
    ): Call<ResponModel>


    @FormUrlEncoded
    @POST("blmnikah")
    fun blmnikah(
        @Field("nik") nik: String,
        @Field("nama_lengkap") nama: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("agama") agama: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("keramaian")
    fun keramaian(
        @Field("nik") nik: String,
        @Field("nama_lengkap") nama: String,
        @Field("umur") umur: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String,
        @Field("tgl_keramaian") tanggal_lahir: String,
        @Field("tempat_keramaian") tempat_lahir: String,
        @Field("kegiatan") jenis_kelamin: String

    ): Call<ResponModel>


}