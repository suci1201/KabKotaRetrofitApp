
package com.example.kabkotaretrofitapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kabkotaretrofitapp.databinding.ActivityViewPetaBinding
import com.example.kabkotaretrofitapp.helpers.Config

class ViewPetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val urlPeta = intent.getStringExtra(Config.EXTRA_URL_PETA)

        Log.d("PETA_DEBUG", "URL peta: $urlPeta")

        if (urlPeta.isNullOrBlank()) {
            Toast.makeText(
                this,
                "URL peta kosong",
                Toast.LENGTH_LONG
            ).show()
            finish()
            return
        }

        Glide.with(this)
            .load(urlPeta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_dialog_alert)
            .fitCenter()
            .into(binding.imgItemPhoto)
    }
}
