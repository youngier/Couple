package com.cloudon.couple.ui.anniversay.type

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudon.couple.application.MainApplication
import com.cloudon.couple.databinding.ActivityTypeListBinding
import com.cloudon.couple.ui.EditDialog
import com.cloudon.couple.ui.OnEditClickListener
import java.util.Collections


class TypeListActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityTypeListBinding

    private lateinit var viewModel: TypeViewModel

    private var adapter: TypeAdapter? = null

    private var editDialog: EditDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTypeListBinding.inflate(LayoutInflater.from(this))
        setContentView(_binding.root)

        viewModel =
            ViewModelProvider(this)[TypeViewModel::class.java]

        initObserver()

        adapter = TypeAdapter(this)
        adapter?.setOnItemListener(object : TypeAdapter.OnItemListener {
            override fun onDelete(position: Int) {
                var result = adapter?.getData()?.removeAt(position)
                adapter?.notifyItemRemoved(position)
                result?.let {
                    viewModel.removeType(result.title)
                }
            }

        })
        _binding.rv.layoutManager = LinearLayoutManager(this)
        _binding.rv.adapter = adapter

        _binding.tvAdd.setOnClickListener {
            showAddTypeDialog()
        }
        _binding.tvBack.setOnClickListener {
            finish()
        }

        _binding.tvSave.setOnClickListener {
            adapter?.getSelectedData()?.toList()?.let {
                MainApplication.selectedType.addAll(it)
            }
            setResult(RESULT_OK)
            finish()
        }

        setupTouchSort()
    }

    private fun setupTouchSort() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun isLongPressDragEnabled(): Boolean {
                return true // 允许长按拖动
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return false // 不允许滑动
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN // 允许上下拖动
                return makeMovementFlags(dragFlags, 0)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // 交换item位置
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                adapter?.let {
                    if (fromPosition < toPosition) {
                        for (i in fromPosition until toPosition) {
                            Collections.swap(it.getData(), i, i + 1)
                        }
                    } else {
                        for (i in fromPosition downTo toPosition + 1) {
                            Collections.swap(it.getData(), i, i - 1)
                        }
                    }
                    it.notifyItemMoved(fromPosition, toPosition)
                }
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 不处理滑动事件
            }
        })
        itemTouchHelper.attachToRecyclerView(_binding.rv)

    }

    private fun showAddTypeDialog() {
        if (editDialog == null) {
            editDialog = EditDialog(this, "新增分类", "请输入分类名称")
            editDialog?.setOnEditClickListener(object : OnEditClickListener {
                override fun onConfirm(content: String) {
                    addType(content)
                }

                override fun onCancel() {

                }

            })
        }
        editDialog?.show()

    }

    private fun initObserver() {
        viewModel.type.observe(this) {
            adapter?.setData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllType()
    }

    private fun addType(type: String) {
        viewModel.insertType(type)
    }
}