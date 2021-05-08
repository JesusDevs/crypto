package com.example.crypto.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto.R
import com.example.crypto.databinding.FragmentFirstBinding
import com.example.crypto.viewmodel.ViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: ViewModel  by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CryptoAdapter()
        binding.rvView.adapter = adapter
        binding.rvView.layoutManager = LinearLayoutManager(context)

        viewModel.cryptoLiveDataFromBase.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("LISTADO", "$it")
                adapter.updateList(it)

            }
        })



        adapter.selectedItem().observe(viewLifecycleOwner, Observer {

            it?.let {
                //pasando datos con bundle
                val bundle = Bundle()
                bundle.putString("id", it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
                //pasando datos con viewModel
                viewModel.selected(it)
            }
        })

    }
}