package com.cloudon.couple.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import com.cloudon.couple.databinding.DialogEditBinding

class EditDialog(
    private val context: Context,
    private val title: String,
    private val hint: String,
) : Dialog(context) {

    private lateinit var _binding: DialogEditBinding

    private var listener: OnEditClickListener? = null

    fun setOnEditClickListener(listener: OnEditClickListener) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogEditBinding.inflate(LayoutInflater.from(context))
        setContentView(_binding.root)
        _binding.tvTitle.text = title
        _binding.etContent.hint = hint
        _binding.tvConfirm.setOnClickListener {
            listener?.onConfirm(_binding.etContent.text.toString())
            _binding.etContent.setText("")
            dismiss()
        }
        _binding.tvCancel.setOnClickListener {
            listener?.onCancel()
            _binding.etContent.setText("")
            dismiss()
        }

        // 设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        val dialogWindow = window
        val manager = (context as Activity).windowManager
        val params = dialogWindow?.attributes
        // 获取对话框当前的参数值
        dialogWindow?.setGravity(Gravity.CENTER) //设置对话框位置
        // 获取屏幕宽、高度
        val d = manager.defaultDisplay
        // 宽度设置为屏幕的0.9，根据实际情况调整
        params?.width = (d.width * 0.8).toInt()
        dialogWindow?.attributes = params
    }

}

interface OnEditClickListener {
    fun onConfirm(content: String)

    fun onCancel()
}