package me.shouheng.samples.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import me.shouheng.samples.R
import me.shouheng.utils.data.EncryptUtils
import me.shouheng.utils.ktx.*

class TestEncryptUtilsActivity : AppCompatActivity() {
    private var et: EditText? = null
    private var tvResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_encrypt_utils)
        et = findViewById(R.id.et)
        tvResult = findViewById(R.id.tv_result)
    }

    fun doEncrypt(view: View?) {
        val s = et!!.text.toString()
        val result = "\n\n".join(listOf(
                "MD2 : ${s.md2()}",
                "MD5 : ${s.md5()}",
                "SHA1 : ${s.sha1()}",
                "SHA224 : ${s.sha224()}",
                "SHA256 : ${s.sha256()}",
                "SHA384 : ${s.sha384()}",
                "SHA512 : ${s.sha512()}"))
        tvResult!!.text = result
    }
}