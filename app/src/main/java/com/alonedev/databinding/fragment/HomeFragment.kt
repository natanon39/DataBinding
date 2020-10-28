package com.alonedev.databinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.GridLayoutManager
import com.alonedev.databinding.R
import com.alonedev.databinding.databinding.FragmentHomeBinding
import com.alonedev.databinding.model.CatList
import com.alonedev.databinding.model.DataList
import com.alonedev.databinding.viewmodel.CatViewModel
import com.alonedev.databinding.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    lateinit var userViewModel : UserViewModel
    lateinit var catViewModel : CatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = makeAPICall(this)
        catViewModel = cmakeAPICall(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val bindign:FragmentHomeBinding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        bindign.viewmodel=userViewModel
        bindign.catviewmodel=catViewModel

        bindign.recyclerView.apply{
            adapter=userViewModel.getAdapter()

            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)

        }
        bindign.catPager.apply{
            adapter=catViewModel.getAdapter()
        }
        TabLayoutMediator(bindign.tabindi, bindign.catPager) { tab, position ->
            //Some implementation
        }.attach()

        return bindign.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

//    fun setupBinding(viewModel: UserViewModel, catViewModel: CatViewModel) {
//        val data: FragmentHomeBinding = DataBindingUtil.setContentView(
//            requireActivity(),
//            R.layout.fragment_home
//        )
//        data.setVariable(BR.viewmodel, viewModel)
//        data.setVariable(BR.catviewmodel, catViewModel)
//        data.executePendingBindings()
////        catPager.apply {
////
////        }
//    }


    private fun makeAPICall(owner: LifecycleOwner): UserViewModel {
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getDataObserver().observe(owner, Observer<DataList> {
            if (it != null) {
                viewModel.setAdapterData(it.data)
            } else {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall()

        return viewModel
    }

    private fun cmakeAPICall(owner: LifecycleOwner): CatViewModel {
        val viewModel = ViewModelProvider(this).get(CatViewModel::class.java)
        viewModel.getDataObserver().observe(owner, Observer<CatList> {
            if (it != null) {
                viewModel.setAdapterData(it.data)
            } else {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall()

        return viewModel
    }


}