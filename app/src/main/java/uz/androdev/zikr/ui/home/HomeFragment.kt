package uz.androdev.zikr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.androdev.zikr.MainActivity
import uz.androdev.zikr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: ZikrAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        subscribeToLiveData()
    }

    private fun initUI() {
        adapter = ZikrAdapter()
        binding?.recyclerView?.adapter = adapter

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

        viewModel.refreshing.observe(viewLifecycleOwner){ isRefreshing -> }
    }

    fun onRefresh() {
        viewModel.refreshList()
    }
}