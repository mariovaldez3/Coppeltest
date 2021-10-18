package com.mariovaldez.coppeltest.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mariovaldez.coppeltest.adapter.SearchAdapter
import com.mariovaldez.coppeltest.databinding.FragmentSearchBinding
import com.mariovaldez.coppeltest.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    lateinit var binding : FragmentSearchBinding
    private val viewmodel: SearchViewModel by viewModels()

    private lateinit var adapterSearch:SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        setupListeners()
        setupAdapters()


        return binding.root
    }

    private fun setupAdapters() {
        adapterSearch = SearchAdapter()
        binding.recyclerSearch.apply {
            adapter = adapterSearch
            adapter!!.notifyDataSetChanged()
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
        }
    }

    private fun setupListeners() {
        viewmodel.init()
        viewmodel.heroResult.observe(viewLifecycleOwner,{
            if(it!=null){
                binding.progressbar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                adapterSearch.heroItems = it
            }
        })

        viewmodel.errorNotFound.observe(viewLifecycleOwner,{
            if(it!=null) {
                binding.progressbar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
                binding.notFound.text = it
            }
        })
        viewmodel.error.observe(viewLifecycleOwner,{
            if(it!=null){
                binding.progressbar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                showDialog(it)
            }
        })

        binding.searchButton.setOnClickListener {
            if (binding.search.text!!.isNotEmpty()) {
                adapterSearch.heroItems = emptyList()
                binding.notFound.visibility = View.GONE
                binding.progressbar.visibility = View.VISIBLE
                search()
            }
        }
    }
    private fun showDialog(message:String) {
        val dlgAlert: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireActivity())
        dlgAlert.setMessage(message)
        dlgAlert.setTitle("Try Again")
        dlgAlert.setCancelable(true)
        dlgAlert.setNegativeButton("OK") { dialog, which -> dialog.cancel() }
        dlgAlert.create().show()
    }

    private fun search() {
        val value : String = binding.search.text.toString()
        hideKeyboard()
        viewmodel.search(value)
    }
    private fun hideKeyboard(){
        val view : View? = requireActivity().currentFocus
        if(view != null){
            val inputManager: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

}