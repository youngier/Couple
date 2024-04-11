package com.cloudon.couple.ui.anniversay

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudon.couple.databinding.FragmentAnniversaryBinding

class AnnerversaryFragment : Fragment() {

    private var _binding: FragmentAnniversaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mAdapter: AnniversaryAdapter

    private lateinit var mViewModel: AnniversaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            ViewModelProvider(this)[AnniversaryViewModel::class.java]

        _binding = FragmentAnniversaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initObserver()

        _binding?.tvAdd?.setOnClickListener {
            startActivity(Intent(activity, AnniversaryAddActivity::class.java))
        }

        context?.let {
            mAdapter = AnniversaryAdapter(it)
            _binding?.rvAnniversary?.layoutManager = LinearLayoutManager(it)
            _binding?.rvAnniversary?.adapter = mAdapter
        }

        return root
    }

    private fun initObserver() {
        mViewModel.anniversary.observe(viewLifecycleOwner) {
            it?.let {
                mAdapter.setData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getAllAnniversary()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}