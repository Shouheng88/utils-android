package me.shouheng.samples.stability

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import me.shouheng.samples.R
import me.shouheng.samples.common.BaseActivity
import me.shouheng.utils.stability.L

class TestLogActivity : BaseActivity() {
    private var cnt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_test)
        val et = findViewById<EditText>(R.id.et)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            cnt++
            when (cnt % 6) {
                0 -> L.v("Logging v")
                1 -> L.d("Logging d")
                2 -> L.i("Logging i")
                3 -> L.w("Logging w")
                4 -> L.e("Logging e")
                5 -> L.a("Logging a")
                else -> { /*noop*/ }
            }
        }
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /*noop*/ }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { /*noop*/ }

            override fun afterTextChanged(s: Editable) { L.d(s) }
        })
    }
}