package com.bangkitc23ps404.huze.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitc23ps404.huze.adapter.HomeViewAdapter1
import com.bangkitc23ps404.huze.adapter.HomeViewAdapter2
import com.bangkitc23ps404.huze.adapter.HomeViewAdapter3
import com.bangkitc23ps404.huze.data.network.response.ArtikelItem
import com.bangkitc23ps404.huze.data.network.response.CatsItem
import com.bangkitc23ps404.huze.data.network.response.DogsItem
import com.bangkitc23ps404.huze.databinding.FragmentHomeBinding
import com.bangkitc23ps404.huze.ui.detail.DetailActivity
import com.bangkitc23ps404.huze.ui.detail.DetailActivityArtikel
import com.bangkitc23ps404.huze.ui.detail.DetailActivityCats

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by viewModels<HomeViewModel>()
    private val binding get() = _binding!!
    private lateinit var adapterDogs : HomeViewAdapter1
    private lateinit var adapterCats : HomeViewAdapter2
    private lateinit var adapterArtikel : HomeViewAdapter3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initDogsRV()
        initCatsRV()
        initArtikelRV()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDogsRV()
    {
        _binding!!.apply {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvDogs.layoutManager = layoutManager


            homeViewModel.listDog.observe(viewLifecycleOwner) { items ->
                val dogList = ArrayList<DogsItem>()
                dogList.addAll(items)
                adapterDogs = HomeViewAdapter1(dogList)
                rvDogs.adapter = adapterDogs

                adapterDogs.onItemClick(object : HomeViewAdapter1.OnClickListener {
                    override fun onItemClick(item: DogsItem) {
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .also {
                                    it.putExtra(DetailActivity.EXTRA_DOG, item)
                                })
                    }
                })
            }
        }
    }

    private fun initCatsRV()
    {
        _binding!!.apply {
            val layoutManagerCats =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvCats.layoutManager = layoutManagerCats



            homeViewModel.listCat.observe(viewLifecycleOwner) { items ->
                val catList = ArrayList<CatsItem>()
                catList.addAll(items)
                adapterCats = HomeViewAdapter2(catList)
                rvCats.adapter = adapterCats
                adapterCats.onItemClick(object : HomeViewAdapter2.OnClickListener{
                    override fun onItemClick(item: CatsItem) {
                        startActivity(
                            Intent(context, DetailActivityCats::class.java)
                                .also {
                                    it.putExtra(DetailActivityCats.EXTRA_CAT, item)
                                })
                    }
                })
            }
        }
    }

    private fun initArtikelRV()
    {
        _binding!!.apply {
            val layoutManagerArtikel =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvArtikel.layoutManager = layoutManagerArtikel



            homeViewModel.listArtikel.observe(viewLifecycleOwner) { items ->
                val ArtikelList = ArrayList<ArtikelItem>()
                ArtikelList.addAll(items)
                adapterArtikel = HomeViewAdapter3(ArtikelList)
                rvArtikel.adapter = adapterArtikel
                adapterArtikel.onItemClick(object : HomeViewAdapter3.OnClickListener{
                    override fun onItemClick(item: ArtikelItem) {
                        startActivity(
                            Intent(context, DetailActivityArtikel::class.java)
                                .also {
                                    it.putExtra(DetailActivityArtikel.EXTRA_ARTIKEL, item)
                                })
                    }
                })
            }
        }
    }

}