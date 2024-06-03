package com.example.utsandroid_pemro3_10121912_vincensius

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class CheckDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_data)

        val cekTes: TextView = findViewById(R.id.cek_tes)
        val cekKonfirmasi: TextView = findViewById(R.id.cek_konfirmasi)
        val cekNik: TextView = findViewById(R.id.cek_nik_text)
        val cekNama: TextView = findViewById(R.id.cek_nama_text)
        val cekLahir: TextView = findViewById(R.id.cek_lahir_text)
        val cekKelamin: TextView = findViewById(R.id.cek_kelamin_text)
        val cekHubungan: TextView = findViewById(R.id.cek_hubungan_text)
        val buttonSimpan: Button = findViewById(R.id.simpan)
        val buttonUbah: Button = findViewById(R.id.ubah)

        cekTes.text = intent.getStringExtra("JENIS_TES")
        cekKonfirmasi.text = intent.getStringExtra("TANGGAL_KONFIRMASI")
        cekNik.text = intent.getStringExtra("NIK")
        cekNama.text = intent.getStringExtra("NAMA")
        cekLahir.text = intent.getStringExtra("TANGGAL_LAHIR")
        cekKelamin.text = intent.getStringExtra("JENIS_KELAMIN")
        cekHubungan.text = intent.getStringExtra("HUBUNGAN")

        buttonSimpan.setOnClickListener {
            showBottomSheetDialog()
        }

        buttonUbah.setOnClickListener {
            finish()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetDialog.behavior.setPeekHeight(resources.displayMetrics.heightPixels)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetDialog.window?.setWindowAnimations(R.style.BottomSheetAnimation)

        bottomSheetDialog.show()
    }
}

