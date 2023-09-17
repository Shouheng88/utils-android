package me.shouheng.samples.data

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import me.shouheng.samples.R
import me.shouheng.utils.app.ResUtils
import me.shouheng.utils.data.EncodeUtils
import me.shouheng.utils.data.EncryptUtils
import me.shouheng.utils.ktx.*
import me.shouheng.utils.stability.L
import me.shouheng.utils.store.IOUtils
import me.shouheng.utils.store.PathUtils
import me.shouheng.utils.store.ZipUtils
import java.io.File
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class TestEncryptUtilsActivity : AppCompatActivity() {
    private var et: EditText? = null
    private var tvResult: TextView? = null
    private var tvDeResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_encrypt_utils)
        et = findViewById(R.id.et)
        tvResult = findViewById(R.id.tv_result)
        tvDeResult = findViewById(R.id.tv_dresult)
        tvResult?.onDebouncedClick {
            copy(this, tvResult!!.text.toString())
            toast("copied")
        }
    }

    fun doEncrypt(view: View?) {
        val s = et!!.text.toString()
        val result = "\n\n".join(
            listOf(
                "MD2 : ${s.md2()}",
                "MD5 : ${s.md5()}",
                "SHA1 : ${s.sha1()}",
                "SHA224 : ${s.sha224()}",
                "SHA256 : ${s.sha256()}",
                "SHA384 : ${s.sha384()}",
                "SHA512 : ${s.sha512()}",
                "DES : ${EncryptUtils.encryptDES2HexString(s.toByteArray(), "1234577ddccDEFC1".toByteArray(), "DES", null)}")
        )
        tvResult!!.text = result
    }

    fun doDecrept(view: View?) {
        val s = findViewById<AppCompatEditText>(R.id.et_de)!!.text.toString()
        val result = "\n\n".join(
            listOf(
                "DES: ${String(EncryptUtils.decryptHexStringDES(s, "1234577ddccDEFC1".toByteArray(), "DES", null))}"
            )
        )
        tvDeResult!!.text = result
    }

    fun doZipDecrypt(view: View?) {
        val file = File(PathUtils.getExternalAppFilesPath(), "test.zip")
        val target = File(PathUtils.getExternalAppFilesPath(), "target")
        IOUtils.writeFileFromIS(file, ResUtils.getAssets().open("data.zip"))
        ZipUtils.unzipFile(file, target)

        val appIv = ""
        val appKey = ""

        val dataIv = ""
        val dataKey = ""

        val infoFile = File(target, ".data/.info")
        val bytes = infoFile.readBytes()
        val magicLength = "PASSCARE".toByteArray().size
        val encrypted = EncryptUtils.encryptAES(
            "LEGAL INSTITUTION IS THE ONLY WAY LEADS TO A MORDEN SOCIETY.".toByteArray(),
            dataKey.toByteArray(),
            "AES/CBC/PKCS5Padding",
            dataIv.toByteArray()
        )
        val encrypted1 = bytes.copyOfRange(magicLength, encrypted.size + magicLength)
        val same = Arrays.equals(encrypted1, encrypted)
        val left = bytes.copyOfRange(encrypted.size + magicLength, bytes.size)
        val json = String(left)
        L.d(json)
        L.d("same[$same]")

        val encryptFile = File(target, ".data/.encrypt")
        val encryptBytes = encryptFile.readBytes()
        val RSA_PUBLICK_KEY = ""
        val rsaPrivateKey = ""
        val encryptKeyBytes = EncryptUtils.decryptRSA(
            encryptBytes.copyOfRange(15, encryptBytes.size),
            EncodeUtils.base64Decode(rsaPrivateKey),
            1024,
            "RSA/ECB/PKCS1Padding"
        )
        val encryptedAndroid = EncryptUtils.encryptRSA((dataIv + dataKey).toByteArray(),
            EncodeUtils.base64Decode(RSA_PUBLICK_KEY),
            1024,
            "RSA"
        )
        L.d(encryptedAndroid)

        val decoded = EncodeUtils.base64Decode(rsaPrivateKey)
        val priKey: RSAPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(decoded)) as RSAPrivateKey
        val cipher: Cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, priKey)
        L.d(String(cipher.doFinal(encryptBytes.copyOfRange(15, encryptBytes.size))))

        L.d(String(encryptKeyBytes))
    }

    fun doZipEncrypt(view: View?) {
        val file = File(PathUtils.getExternalAppFilesPath(), "reziped.zip")
        val target = File(PathUtils.getExternalAppFilesPath(), "target")
        ZipUtils.zipFile(target, file)
    }

    private fun copy(context: Context, content: String) {
        val clipboardManager = (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        clipboardManager.text = content
    }
}