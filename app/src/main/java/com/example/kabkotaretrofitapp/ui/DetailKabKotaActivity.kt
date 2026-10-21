package com.example.kabkotaretrofitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.example.kabkotaretrofitapp.databinding.ActivityDetailKabKotaBinding
import com.example.kabkotaretrofitapp.helpers.Config
import com.example.kabkotaretrofitapp.model.KabKotaItem

class DetailKabKotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKabKotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKabKotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val json = intent.getStringExtra(Config.EXTRA_KAB_KOTA)

        if (json.isNullOrEmpty()) {
            finish()
            return
        }

        val dataKabKota = Gson().fromJson(
            json,
            KabKotaItem::class.java
        )

        val url = "${Config.LOGO_URL}/${dataKabKota.logo}"

        Glide.with(this)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(binding.imgItemPhoto)

        binding.tvNama.text = dataKabKota.kabupatenKota.orEmpty()
        binding.tvPusatPemerintahan.text =
            dataKabKota.pusatPemerintahan.orEmpty()
        binding.tvBupatiWalikota.text =
            dataKabKota.bupatiWalikota.orEmpty()
        binding.tvTanggalBerdiri.text =
            dataKabKota.tanggalBerdiri.orEmpty()
        binding.tvLuasWilayah.text =
            dataKabKota.luasWilayah?.toString().orEmpty()
        binding.tvJumlahPenduduk.text =
            dataKabKota.jumlahPenduduk?.toString().orEmpty()
        binding.tvJumlahKecamatan.text =
            dataKabKota.jumlahKecamatan?.toString().orEmpty()
        binding.tvJumlahKelurahan.text =
            dataKabKota.jumlahKelurahan?.toString().orEmpty()
        binding.tvJumlahDesa.text =
            dataKabKota.jumlahDesa?.toString().orEmpty()
        binding.tvDeskripsi.text =
            dataKabKota.deskripsi.orEmpty()

        // Aksi tombol Tampilkan Peta Wilayah
        binding.btnViewPeta.setOnClickListener {
            val showViewPeta = Intent(
                this@DetailKabKotaActivity,
                ViewPetaActivity::class.java
            )

            showViewPeta.putExtra(
                Config.EXTRA_URL_PETA,
                dataKabKota.urlPetaWilayah
            )

            startActivity(showViewPeta)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
