package com.vsple.productdetailapplication.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vsple.productdetailapplication.R
import com.vsple.productdetailapplication.databinding.ItemSizeBinding
import com.vsple.productdetailapplication.ui.models.SizeModel
import com.vsple.productdetailapplication.ui.utils.setSafeOnClickListener

class SizeListAdapter(
    private var context: Context,
    private var list: List<SizeModel>?,
    private var oldPosition: Int,
    itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<SizeListAdapter.MyViewHolder>() {

     var itemClickListener: ItemClickListener

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    init {
        selectedItemPos = oldPosition
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): MyViewHolder {
        val binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (list!!.isNotEmpty()) {

            if (list!![position].stock) {
                holder.binding.constTop2.visibility = View.VISIBLE
                holder.binding.tvStock.visibility = View.VISIBLE

            } else {
                holder.binding.constTop2.visibility = View.GONE
                holder.binding.tvStock.visibility = View.GONE
            }


            if (position == selectedItemPos) {
                holder.binding.constTop.background =
                    context.resources.getDrawable(R.drawable.size_selected)
                holder.binding.tvSizePrize.setTextColor(context.resources.getColor(R.color.greenLite))
            } else {
                holder.binding.constTop.background =
                    context.resources.getDrawable(R.drawable.bg_size)
                holder.binding.tvSizePrize.setTextColor(context.resources.getColor(R.color.whiteFFFEFE))
            }
            holder.bind(list!![position])

        }
        holder.binding.constTop.setSafeOnClickListener {
            if (!list!![position].stock) {
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

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    inner class MyViewHolder(val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(result: SizeModel) {


            binding.tvSizePrize.text = result.price
            binding.tvSizeTitle.text = result.title
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