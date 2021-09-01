package uz.androdev.zikr.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.androdev.zikr.ui.MainActivity
import uz.androdev.zikr.databinding.FragmentHomeBinding
import uz.androdev.zikr.ui.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: ZikrAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        subscribeToLiveData()
    }

    private fun initUI() {
        adapter = ZikrAdapter()
        binding.recyclerView.adapter = adapter

        adapter?.onComplete = { zikr ->
            viewModel.removeZikr(zikr)
        }

        (requireActivity() as MainActivity).binding?.refresh?.setOnClickListener {
            viewModel.refreshList()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.zikrList.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }

        viewModel.refreshing.observe(viewLifecycleOwner) { isRefreshing -> }
    }

    fun onRefresh() {
        viewModel.refreshList()
    }
}