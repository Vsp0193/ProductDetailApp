package com.vsple.productdetailapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.vsple.productdetailapplication.R
import com.vsple.productdetailapplication.ui.models.ImageModel

class ViewPagerAdapter(private val mContext: Context, private val itemList: List<ImageModel>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)

        val view =    layoutInflater!!.inflate(R.layout.item_product_image, container, false)
        var imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        imgPoster.setImageDrawable(itemList[position].itemImage)
        container.addView(view, position)

        return view
    }


    override fun getCount(): Int {
        return itemList.size
    }


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}