package com.example.kabkotaretrofitapp.model

import com.google.gson.annotations.SerializedName

data class KabKotaItem(

    @field:SerializedName("kabupaten_kota")
    val kabupatenKota: String? = null,

    @field:SerializedName("jumlah_penduduk")
    val jumlahPenduduk: Int? = null,

    @field:SerializedName("tanggal_berdiri")
    val tanggalBerdiri: String? = null,

    @field:SerializedName("jumlah_kecamatan")
    val jumlahKecamatan: Int? = null,

    @field:SerializedName("bupati_walikota")
    val bupatiWalikota: String? = null,

    @field:SerializedName("pusat_pemerintahan")
    val pusatPemerintahan: String? = null,

    @field:SerializedName("url_peta_wilayah")
    val urlPetaWilayah: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null,

    @field:SerializedName("jumlah_kelurahan")
    val jumlahKelurahan: Int? = null,

    @field:SerializedName("luas_wilayah")
    val luasWilayah: Any? = null,

    @field:SerializedName("jumlah_desa")
    val jumlahDesa: Int? = null
)