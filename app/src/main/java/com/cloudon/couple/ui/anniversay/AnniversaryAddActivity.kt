package com.cloudon.couple.ui.anniversay

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cloudon.couple.databinding.ActivityAnniversaryAddBinding

class AnniversaryAddActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityAnniversaryAddBinding
    private lateinit var viewModel: AnniversaryAddViewModel

    private var anniversaryTime = 0L
    // 类型
    private var anniversaryType = ""
    private var repeatEnable = 0
    private var noticeEnable = 0
    private var anniversaryNoticeType = 0

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
}