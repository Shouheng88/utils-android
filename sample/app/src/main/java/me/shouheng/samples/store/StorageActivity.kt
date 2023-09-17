package me.shouheng.samples.store

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import me.shouheng.samples.databinding.ActivityStorageBinding
import me.shouheng.utils.UtilsApp
import me.shouheng.utils.data.EncodeUtils
import me.shouheng.utils.ktx.join
import me.shouheng.utils.ktx.onDebouncedClick
import me.shouheng.utils.ktx.toast
import me.shouheng.utils.store.IOUtils
import me.shouheng.utils.store.KV
import me.shouheng.utils.store.PathUtils
import java.io.File
import java.util.*

/** To make a sample for storage usage on Android 11. */
class StorageActivity : AppCompatActivity() {

    private var binding: ActivityStorageBinding? = null

    private var request: ActivityResultLauncher<Unit>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)
        buildPermissionRequest()

        binding?.btnRequest?.onDebouncedClick {
            request?.launch(Unit)
        }
        binding?.btnPermission?.onDebouncedClick {
            toast("Permitted: [${isExternalStoragePermissionGranted()}]")
        }
        binding?.btnWrite?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                writeToPath()
            }
        }
        binding?.btnRead?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                readPath()
            }
        }
        binding?.btnDelete?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                delete()
            }
        }
        binding?.btnRenameDir?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                renameDir()
            }
        }
        binding?.btnRenameFile?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                renameFile()
            }
        }
        binding?.btnWriteOld?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                writeByFile()
            }
        }
        binding?.btnListAll?.onDebouncedClick {
            if (isExternalStoragePermissionGranted()) {
                listAll()
            }
        }
        binding?.btnGetGranted?.onDebouncedClick {
            binding?.tv?.text = "persistedUriPermissions:\n" +
                    "\n".join(UtilsApp.getApp().contentResolver.persistedUriPermissions.map { it.toString() }) +
                    "outgoingPersistedUriPermissions:\n" +
                    "\n".join(UtilsApp.getApp().contentResolver.outgoingPersistedUriPermissions.map { it.toString() })
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun buildPermissionRequest() {
        request = registerForActivityResult(object : ActivityResultContract<Unit, Unit>() {
            override fun createIntent(context: Context, input: Unit?): Intent {
                // 授予对目录内容的访问权限，如果是读写文件可以用 ACTION_CREATE_DOCUMENT 或者 ACTION_OPEN_DOCUMENT
                // https://developer.android.com/reference/android/content/Intent#ACTION_OPEN_DOCUMENT_TREE
                // https://developer.android.com/training/data-storage/shared/documents-files?hl=zh-cn#grant-access-directory
                val i = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
                i.flags = (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                        or Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 您可以根据需要使用 EXTRA_INITIAL_URI intent extra 指定文件选择器在首次加载时应显示的目录的 URI。
//                var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata")
//                var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid")
                    var uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary")
                    uri = DocumentFile.fromTreeUri(this@StorageActivity, uri)?.uri
                    i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
                }
                return i
            }

            override fun parseResult(resultCode: Int, intent: Intent?) {
                if (resultCode != Activity.RESULT_OK) return
                try {
                    val uri: Uri = intent?.data ?: return
                    KV.get().put("__external_storage_path", uri.toString())
                    contentResolver.takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {

        }
    }

    private fun writeToPath() {
        val uriString = KV.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            var doc = createOrExistsFile(root, "test_a", "application/txt", "${System.currentTimeMillis()}.txt")
            var ous = this.contentResolver.openOutputStream(doc!!.uri)
            var ret = IOUtils.write2Stream(ous, "sample a")
            toast("Result: $ret")
            doc = createOrExistsFile(root, "test_a/test_a_b", "application/txt", "${System.currentTimeMillis()}.txt")
            ous = this.contentResolver.openOutputStream(doc!!.uri)
            ret = IOUtils.write2Stream(ous, "sample a b")
            toast("Result: $ret")
        } catch (e: Exception) {
            e.printStackTrace()
            toast("failed!")
        }
    }

    private fun readPath() {
        val uriString = KV.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            val name = "sample.txt"
            val doc = createOrExistsFile(root, "test_a", "application/txt", name)
            val ous = this.contentResolver.openOutputStream(doc!!.uri)
            val ret = IOUtils.write2Stream(ous, "sample a")
            toast("Write succeed: $ret")
            val file = getFile(root, "test_a", name)
            val ins = contentResolver.openInputStream(file!!.uri)
            val text = IOUtils.is2String(ins)
            toast("Read: $text")
        } catch (e: Exception) {
            e.printStackTrace()
            toast("failed!")
        }
    }

    private fun delete() {
        val uriString = KV.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            val name = "sample.txt"
            val doc = getFile(root, "test_a", name)
            if (doc == null) {
                toast("File not found!")
            } else {
                val ret = doc.delete()
                toast("Deleted: $ret")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            toast("failed!")
        }
    }

    private fun renameDir() {
        val uriString = KV.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            val doc = getDirectory(root, "test_a")
            if (doc == null) {
                toast("Directory not found!")
            } else {
                val ret = doc.renameTo("test_a_renamed")
                toast("Rename: $ret")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            toast("failed!")
        }
    }

    private fun renameFile() {
        val uriString = KV.get().getString("__external_storage_path")
        try {
            val uri = Uri.parse(uriString)
            val root = DocumentFile.fromTreeUri(this, uri)
            val name = "sample.txt"
            val doc = getFile(root, "test_a", name)
            if (doc == null) {
                toast("File not found!")
            } else {
                val ret = doc.renameTo("sample_renamed.txt")
                toast("Rename: $ret")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            toast("failed!")
        }
    }

    private fun writeByFile() {
        val uriString = KV.get().getString("__external_storage_path")
        val left = uriString.removePrefix("content://com.android.externalstorage.documents/tree/primary%3A")
        val path = EncodeUtils.urlDecode(left)
        val root = PathUtils.getExternalStoragePath()
        val file = File("$root${File.separator}$path", "write_old.text")
        IOUtils.writeFileFromString(file, "test test")
    }

    private fun listAll() {
        val uriString = KV.get().getString("__external_storage_path")
        val left = uriString.removePrefix("content://com.android.externalstorage.documents/tree/primary%3A")
        val path = EncodeUtils.urlDecode(left)
        val root = PathUtils.getExternalStoragePath()
        val files = File("$root${File.separator}$path").list()
        toast(Arrays.toString(files))
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

    /** Get document file under root with given name. */
    private fun getFile(root: DocumentFile?, directoryPath: String, fileName: String): DocumentFile? {
        val dir = getDirectory(root, directoryPath)
        val file = dir?.findFile(fileName)
        return if (file != null && file.isFile ) file else null
    }

    /** Get document file directory under root with given path. */
    private fun getDirectory(root: DocumentFile?, directoryPath: String): DocumentFile? {
        if (root == null) return null
        val parts = directoryPath.split(File.separator).toTypedArray()
        var dir = root
        parts.filter { it.isNotEmpty() }.forEach { part ->
            dir = dir?.listFiles()?.find {
                part == it.name && it.isDirectory
            } ?: return@forEach
        }
        return dir
    }

    /** Is external storage write permission granted. */
    private fun isExternalStoragePermissionGranted(): Boolean {
        val path = KV.get().getString("__external_storage_path")
        if (TextUtils.isEmpty(path)) return false
        val uri1 = Uri.parse(path)
        val documentFile = DocumentFile.fromTreeUri(this, uri1) ?: return false
        return documentFile.canWrite()
    }
}
