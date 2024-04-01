package com.cloudon.couple.ui.anniversay

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import com.cloudon.couple.databinding.DialogSelectDateBinding
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView.OnCalendarSelectListener


class SelectDateDialog(private val context: Context, private val listener: OnSelectDateListener) : Dialog(context) {

    private lateinit var _binding: DialogSelectDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogSelectDateBinding.inflate(LayoutInflater.from(context))
        setContentView(_binding.root)
        // 设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        val dialogWindow = window
        val manager = (context as Activity).windowManager
        val params = dialogWindow?.attributes
        // 获取对话框当前的参数值
        dialogWindow?.setGravity(Gravity.CENTER) //设置对话框位置
        // 获取屏幕宽、高度
        val d = manager.defaultDisplay
        // 宽度设置为屏幕的0.9，根据实际情况调整
        params?.width = d.width
        dialogWindow?.attributes = params

        _binding.tvSelectDate.setOnClickListener {
            dismiss()
            listener.onSelectDate(_binding.calendarView.selectedCalendar)
        }
        _binding.tvCancel.setOnClickListener {
            dismiss()
        }
        _binding.calendarView.setOnCalendarSelectListener(object : OnCalendarSelectListener {
            override fun onCalendarOutOfRange(calendar: Calendar?) {

            }

            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                calendar?.also {
                    _binding.tvSelectDateTitle.text = "${it.month}月${it.day}日"
                    _binding.tvSelectDateLunarTitle.text = "${it.year}${it.lunar}"
                }
            }

        })
    }

    interface OnSelectDateListener {
        fun onSelectDate(selectedCalendar: Calendar)
    }
}