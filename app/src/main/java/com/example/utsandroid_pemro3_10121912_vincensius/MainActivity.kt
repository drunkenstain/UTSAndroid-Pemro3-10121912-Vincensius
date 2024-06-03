package com.example.utsandroid_pemro3_10121912_vincensius

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var requiredTes: TextView
    private lateinit var jenisTesGroup: RadioGroup
    private lateinit var editKonfirmasi: TextView
    private lateinit var requiredTglKonfirmasi: TextView
    private lateinit var editNama: TextView
    private lateinit var requiredNama: TextView
    private lateinit var editLahir: TextView
    private lateinit var requiredTglLahir: TextView
    private lateinit var jenisKelamin: RadioGroup
    private lateinit var requiredKelamin: TextView
    private lateinit var jenisHubungan: RadioGroup
    private lateinit var requiredHubungan: TextView
    private lateinit var selanjutnya: Button
    private lateinit var checkBox: CheckBox
    private lateinit var editNik: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requiredTes = findViewById(R.id.required_tes)
        jenisTesGroup = findViewById(R.id.jenis_tes)
        editKonfirmasi = findViewById(R.id.edit_konfirmasi)
        requiredTglKonfirmasi = findViewById(R.id.required_konfirmasi)
        editNama = findViewById(R.id.edit_nama)
        requiredNama = findViewById(R.id.required_nama)
        editLahir = findViewById(R.id.edit_lahir)
        requiredTglLahir = findViewById(R.id.required_lahir)
        jenisKelamin = findViewById(R.id.jenis_kelamin)
        requiredKelamin = findViewById(R.id.required_kelamin)
        jenisHubungan = findViewById(R.id.hubungan_anda)
        requiredHubungan = findViewById(R.id.required_hubungan)
        selanjutnya = findViewById(R.id.selanjutnya)
        checkBox = findViewById(R.id.checkBox)
        editNik = findViewById(R.id.edit_nik)

        editKonfirmasi.setOnClickListener { showDatePickerDialog(editKonfirmasi) }
        editLahir.setOnClickListener { showDatePickerDialog(editLahir) }

        selanjutnya.setOnClickListener {
            if (validateForm()) {
                navigateToCheckDataActivity()
            }
        }
    }

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                textView.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun validateForm(): Boolean {
        var valid = true

        if (TextUtils.isEmpty(editKonfirmasi.text)) {
            showError(editKonfirmasi, "Harus di isi")
            requiredTglKonfirmasi.visibility = View.VISIBLE
            valid = false
        } else {
            requiredTglKonfirmasi.visibility = View.GONE
        }

        if (jenisTesGroup.checkedRadioButtonId == -1){
            requiredTes.visibility = View.VISIBLE
            valid = false
        } else {
            requiredTes.visibility = View.GONE
        }

        if (TextUtils.isEmpty(editNama.text)) {
            showError(editNama, "Harus di isi")
            requiredNama.visibility = View.VISIBLE
            valid = false
        } else {
            requiredNama.visibility = View.GONE
        }

        if (TextUtils.isEmpty(editLahir.text)) {
            showError(editLahir, "Harus di isi")
            requiredTglLahir.visibility = View.VISIBLE
            valid = false
        } else {
            requiredTglLahir.visibility = View.GONE
        }

        if (jenisKelamin.checkedRadioButtonId == -1){
            requiredKelamin.visibility = View.VISIBLE
            valid = false
        } else {
            requiredKelamin.visibility = View.GONE
        }

        if (jenisHubungan.checkedRadioButtonId == -1){
            requiredHubungan.visibility = View.VISIBLE
            valid = false
        } else {
            requiredHubungan.visibility = View.GONE
        }

        if (!checkBox.isChecked) {
            Toast.makeText(this, "Anda harus menyetujui bahwa data yang diisikan adalah benar", Toast.LENGTH_SHORT).show()
            valid = false
        }
        return valid
    }

    private fun showError(view: TextView, message: String) {
        view.error = message
    }

    private fun navigateToCheckDataActivity() {
        val intent = Intent(this, CheckDataActivity::class.java)

        intent.putExtra("NAMA", editNama.text.toString())
        intent.putExtra("NIK", editNik.text.toString())
        intent.putExtra("TANGGAL_KONFIRMASI", editKonfirmasi.text.toString())
        intent.putExtra("TANGGAL_LAHIR", editLahir.text.toString())
        intent.putExtra("JENIS_TES", findViewById<RadioButton>(jenisTesGroup.checkedRadioButtonId).text)
        intent.putExtra("JENIS_KELAMIN", findViewById<RadioButton>(jenisKelamin.checkedRadioButtonId).text)
        intent.putExtra("HUBUNGAN", findViewById<RadioButton>(jenisHubungan.checkedRadioButtonId).text)

        startActivity(intent)
    }
}