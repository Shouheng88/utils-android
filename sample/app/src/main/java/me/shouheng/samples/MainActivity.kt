package me.shouheng.samples

import android.os.Bundle
import android.view.View
import me.shouheng.samples.activity.TestActivityHelper
import me.shouheng.samples.app.TestAppUtilsActivity
import me.shouheng.samples.common.BaseActivity
import me.shouheng.samples.common.FileUtils
import me.shouheng.samples.data.TestEncryptUtilsActivity
import me.shouheng.samples.data.TestTimeUtilsActivity
import me.shouheng.samples.device.TestDeviceUtilsActivity
import me.shouheng.samples.device.TestNetworkUtilsActivity
import me.shouheng.samples.device.TestShellActivity
import me.shouheng.samples.intent.TestIntentActivity
import me.shouheng.samples.permission.TestPermissionActivity
import me.shouheng.samples.stability.TestCrashActivity
import me.shouheng.samples.stability.TestLogActivity
import me.shouheng.samples.store.StorageActivity
import me.shouheng.samples.store.TestPathUtilsActivity
import me.shouheng.samples.store.TestSpUtilsActivity
import me.shouheng.samples.ui.TestAnimUtilsActivity
import me.shouheng.samples.ui.TestImageUtilsActivity
import me.shouheng.samples.ui.TestToastUtilsActivity
import me.shouheng.samples.ui.TestViewUtilsActivity
import me.shouheng.utils.ktx.drawableOf
import me.shouheng.utils.ktx.intOf
import me.shouheng.utils.ktx.start
import me.shouheng.utils.ktx.stringOf
import me.shouheng.utils.permission.PermissionUtils
import me.shouheng.utils.stability.CrashHelper
import me.shouheng.utils.stability.L

/**
 * @author shouh
 * @version $Id: MainActivity, v 0.1 2018/11/22 12:10 shouh Exp$
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_permission).setOnClickListener { start(TestPermissionActivity::class.java) }
        findViewById<View>(R.id.btn_activity_helper).setOnClickListener { start(TestActivityHelper::class.java) }
        findViewById<View>(R.id.btn_crash).setOnClickListener { start(TestCrashActivity::class.java) }
        findViewById<View>(R.id.btn_log).setOnClickListener { start(TestLogActivity::class.java) }
        PermissionUtils.checkStoragePermission(this, {
            CrashHelper.init(application, FileUtils.getExternalStoragePublicCrashDir()) { crashInfo, e -> L.e(crashInfo) }
            L.getConfig().setLog2FileSwitch(true)
                    .setDir(FileUtils.getExternalStoragePublicLogDir())
                    .setFileFilter(L.W)
        })
        stringOf(R.string.activity_helper_activity_result)
        intOf(R.integer.ease_in)
        drawableOf(R.drawable.ic_launcher_background)
    }

    override fun onStop() {
        super.onStop()
        L.d("STOP MAIN")
    }

    override fun onPause() {
        super.onPause()
        L.d("PAUSE MAIN")
    }

    fun testIntentUtils(view: View?) { start(TestIntentActivity::class.java) }

    fun testShellUtils(view: View?) { start(TestShellActivity::class.java) }

    fun testAppUtils(v: View?) { start(TestAppUtilsActivity::class.java) }

    fun testSPUtils(v: View?) { start(TestSpUtilsActivity::class.java) }

    fun testStorageUtils(v: View?) { start(StorageActivity::class.java) }

    fun testDeviceUtils(v: View?) { start(TestDeviceUtilsActivity::class.java) }

    fun testNetworkUtils(v: View?) { start(TestNetworkUtilsActivity::class.java) }

    fun testPathUtils(v: View?) { start(TestPathUtilsActivity::class.java) }

    fun testImageUtils(v: View?) { start(TestImageUtilsActivity::class.java) }

    fun testViewUtils(view: View?) { start(TestViewUtilsActivity::class.java) }

    fun testEncryptUtils(view: View?) { start(TestEncryptUtilsActivity::class.java) }

    fun testTimeUtils(view: View?) { start(TestTimeUtilsActivity::class.java) }

    fun testToastUtils(view: View?) { start(TestToastUtilsActivity::class.java) }

    fun testAnimUtils(view: View?) { start(TestAnimUtilsActivity::class.java) }
}