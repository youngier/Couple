package com.cloudon.couple.ui.anniversay

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cloudon.couple.application.MainApplication
import com.cloudon.couple.databinding.ActivityAnniversaryAddBinding
import com.cloudon.couple.ui.anniversay.type.TypeListActivity
import com.cloudon.couple.utils.TimeUtils
import com.haibin.calendarview.Calendar

class AnniversaryAddActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityAnniversaryAddBinding
    private lateinit var viewModel: AnniversaryAddViewModel

    private var anniversaryTime = 0L
    // 类型
    private var anniversaryType = ""
    private var repeatEnable = 0
    private var noticeEnable = 0
    private var anniversaryNoticeType = 0

    private var selectDateDialog: SelectDateDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAnniversaryAddBinding.inflate(LayoutInflater.from(this))
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this)[AnniversaryAddViewModel::class.java]

        initData()

        viewModel.anniversaryAdd.observe(this) {
            Toast.makeText(this@AnniversaryAddActivity, "保存成功！", Toast.LENGTH_SHORT).show()
            finish()
        }

        _binding.tvBack.setOnClickListener {
            finish()
        }
        _binding.tvSelectDate.setOnClickListener {
            showDateDialog()
        }
        _binding.tvType.setOnClickListener {
            startActivityForResult(Intent(this, TypeListActivity::class.java), 100)
        }
        _binding.tvSave.setOnClickListener {
            saveAnniversary()
        }
    }

    private fun initData() {
        anniversaryTime = System.currentTimeMillis()
    }

    private fun saveAnniversary() {
        val anniversaryTitle = _binding?.title ?: ""
        if (anniversaryTitle.isNullOrBlank()) {
            Toast.makeText(this, "请填写标题", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.saveAnniversary(AnniversaryBean().apply {
            title = anniversaryTitle
            description = _binding?.desciption ?: ""
            time = anniversaryTime
            lunar = if (_binding?.enableLunar == true) 1 else 0
            type = anniversaryType
            repeat = repeatEnable
            notice = noticeEnable
            noticeType = anniversaryNoticeType
            top = if (_binding?.enableLunar == true) 1 else 0
        })
    }

    private fun showDateDialog() {
        if (selectDateDialog == null) {
            selectDateDialog =  SelectDateDialog(this, object : SelectDateDialog.OnSelectDateListener {
                override fun onSelectDate(value: Calendar) {
                    _binding.tvSelectDate.text = "${value.year}年${value.month}月${value.day}日\n${TimeUtils.numberToMonth(value.lunarCalendar.month)}${value.lunar}"
                }
            })

        }
        selectDateDialog?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            var types = ""
            MainApplication.selectedType.forEach {
                types += it.title + ", "
            }
            if (!TextUtils.isEmpty(types)) {
                types = types.substring(0, types.length - 1)
            }
            if (!TextUtils.isEmpty(types)) {
                _binding.tvType.setText(types)
            }
        }
    }
}