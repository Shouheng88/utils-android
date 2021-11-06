package me.shouheng.samples.store

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.text.TextUtils
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import me.shouheng.samples.R
import me.shouheng.utils.ktx.onDebouncedClick
import me.shouheng.utils.ktx.toast
import me.shouheng.utils.store.IOUtils
import me.shouheng.utils.store.SPUtils
import java.io.File
import java.io.IOException
import java.io.OutputStream

/** To make a sample for storage usage on Android 11. */
class StorageActivity : AppCompatActivity() {

    private var request: ActivityResultLauncher<Unit>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        buildPermissionRequest()
        findViewById<View>(R.id.btn_request).onDebouncedClick {
            request?.launch(Unit)
        }
        findViewById<View>(R.id.btn_permission).onDebouncedClick {
            toast("Permitted: [${isExternalStoragePermissionGranted()}]")
        }
        findViewById<View>(R.id.btn_write).onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                writeToPath()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun buildPermissionRequest() {
        request = registerForActivityResult(object : ActivityResultContract<Unit, Unit>() {
            override fun createIntent(context: Context, input: Unit?): Intent {
//                var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata")
//                var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid")
                var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary")
                uri = DocumentFile.fromTreeUri(this@StorageActivity, uri)?.uri
                val i = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
                i.flags = (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                        or Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
                }
                return i
            }

            override fun parseResult(resultCode: Int, intent: Intent?) {
                if (resultCode != Activity.RESULT_OK) return
                try {
                    val uri: Uri = intent?.data ?: return
                    SPUtils.get().put("__external_storage_path", uri.toString())
                    contentResolver.takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {}
    }

    private fun writeToPath() {
        val uriString = SPUtils.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            var doc = createOrExistsFile(root, "test_a", "application/txt", "${System.currentTimeMillis()}.txt")
            var ous = this.contentResolver.openOutputStream(doc!!.uri)
            var ret = writeToOutputStream(ous, "sample a")
            toast("Result: $ret")
            doc = createOrExistsFile(root, "test_a/test_a_b", "application/txt", "${System.currentTimeMillis()}.txt")
            ous = this.contentResolver.openOutputStream(doc!!.uri)
            ret = writeToOutputStream(ous, "sample a b")
            toast("Result: $ret")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun writeToOutputStream(ous: OutputStream?, text: String): Boolean {
        return try {
            ous?.write(text.toByteArray())
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } finally {
            IOUtils.safeCloseAll(ous)
        }
    }

    private fun createOrExistsFile(
        root: DocumentFile?,
        directoryPath: String,
        mimeType: String,
        fileName: String
    ): DocumentFile? {
        if (root == null) return null
        val dir = createOrExistsDirectory(root, directoryPath)
        val file = dir?.findFile(fileName)
        return if (file != null && file.isFile) file else dir?.createFile(mimeType, fileName)
    }

    private fun createOrExistsDirectory(root: DocumentFile?, directoryPath: String): DocumentFile? {
        if (root == null) return null
        val parts = directoryPath.split(File.separator).toTypedArray()
        var dir = root
        parts.filter { it.isNotEmpty() }.forEach { part ->
            dir = dir?.listFiles()?.find {
                part == it.name && it.isDirectory
            } ?: dir?.createDirectory(part)
        }
        return dir
    }

    /** Is external storage write permission granted. */
    private fun isExternalStoragePermissionGranted(): Boolean {
        val path = SPUtils.get().getString("__external_storage_path")
        if (TextUtils.isEmpty(path)) return false
        val uri1 = Uri.parse(path)
        val documentFile = DocumentFile.fromTreeUri(this, uri1) ?: return false
        return documentFile.canWrite()
    }
}
