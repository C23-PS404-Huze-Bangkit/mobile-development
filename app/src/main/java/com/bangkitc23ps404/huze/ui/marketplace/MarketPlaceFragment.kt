package com.bangkitc23ps404.huze.ui.marketplace

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.ProductsItem
import com.bangkitc23ps404.huze.databinding.FragmentMarketPlaceBinding
import com.bangkitc23ps404.huze.ui.detail.DetailMarketPlaceActivity

class MarketPlaceFragment : Fragment() {

    private lateinit var binding: FragmentMarketPlaceBinding
    private lateinit var adapter: ProductAdapter
    private val marketplaceViewModel by viewModels<MarketplaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketPlaceBinding.inflate(inflater, container, false)
        initProductRV()
        return binding.root
    }

    private fun initProductRV() {
        binding!!.apply {
            val layoutManager =
                GridLayoutManager(requireContext(), 2)
            recyclerView.layoutManager = layoutManager


            marketplaceViewModel.productList.observe(viewLifecycleOwner) { items ->
                val productList = ArrayList<ProductsItem>()
                productList.addAll(items)
                adapter = ProductAdapter(productList)
                recyclerView.adapter = adapter
                adapter.onItemClick(object : ProductAdapter.OnClickListener{
                    override fun onItemClick(item: ProductsItem) {
                        startActivity(
                            Intent(context, DetailMarketPlaceActivity::class.java)
                                .also {
                                    it.putExtra(DetailMarketPlaceActivity.EXTRA_PRODUCT, item)
                                })
                    }
                })
            }
        }
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_item_spacing)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, true))
    }
    class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
    }
}