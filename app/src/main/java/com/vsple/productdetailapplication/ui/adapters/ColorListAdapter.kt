package com.vsple.productdetailapplication.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vsple.productdetailapplication.R
import com.vsple.productdetailapplication.databinding.ItemColorBinding
import com.vsple.productdetailapplication.ui.models.ColorModel
import com.vsple.productdetailapplication.ui.utils.setSafeOnClickListener



class ColorListAdapter(
    private var context: Context,
    private var list: List<ColorModel>?,
    private var oldPosition: Int,
    itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<ColorListAdapter.MyViewHolder>() {

    var itemClickListener: ItemClickListener

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    init {
        selectedItemPos = oldPosition
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): MyViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (list!!.isNotEmpty()) {




            if (position == selectedItemPos) {
                holder.binding.constTop.background =
                    context.resources.getDrawable(R.drawable.size_selected)
                holder.binding.tvSizeTitle.setTextColor(context.resources.getColor(R.color.greenLite))
            } else {
                holder.binding.constTop.background =
                    context.resources.getDrawable(R.drawable.bg_size)
                holder.binding.tvSizeTitle.setTextColor(context.resources.getColor(R.color.whiteFFFEFE))
            }
            holder.bind(list!![position])

        }
        holder.binding.constTop.setSafeOnClickListener {

                selectedItemPos = position
                if (lastItemSelectedPos == -1)
                    lastItemSelectedPos = selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
                itemClickListener.onClick(
                    selectedItemPos
                )


        }

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    inner class MyViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(result: ColorModel) {


            binding.tvSizeColor.setBackgroundColor(result.color)
            binding.tvSizeTitle.text = result.name

        }
    }

    // Create constructor
    init {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {

        fun onClick(s: Int?)
        fun onClickPosition(s: Int?)
    }

}