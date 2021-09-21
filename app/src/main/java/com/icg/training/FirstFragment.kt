package com.icg.training

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icg.training.adapter.AndroidInfoAdapter
import com.icg.training.data_provider.DataProvider
import com.icg.training.databinding.FragmentFirstBinding
import com.icg.training.model.InfoModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "RecyclerView"
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initView()
        initClicks()
        loadData()

        return binding.root

    }

    private fun initView() {
        binding.recyclerAndroidVersions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AndroidInfoAdapter(::onItemClicked, ::showDeleteAlert)
        }
    }

    private fun initClicks(){

    }

    private fun onItemClicked(selectedItem:InfoModel?){
        Toast.makeText(context, selectedItem?.androidName, Toast.LENGTH_SHORT).show()
    }
    private fun loadData(){
        (binding.recyclerAndroidVersions.adapter as AndroidInfoAdapter).versionListItems =
            DataProvider.getAndroidDetails()
    }
    private fun showDeleteAlert(deleteItemPosition:Int){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}