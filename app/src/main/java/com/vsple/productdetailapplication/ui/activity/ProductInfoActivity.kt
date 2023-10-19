package com.vsple.productdetailapplication.ui.activity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vsple.productdetailapplication.R
import com.vsple.productdetailapplication.databinding.ActivityProductInfoBinding
import com.vsple.productdetailapplication.ui.adapters.ColorListAdapter
import com.vsple.productdetailapplication.ui.adapters.SizeListAdapter
import com.vsple.productdetailapplication.ui.adapters.ViewPagerAdapter
import com.vsple.productdetailapplication.ui.models.ColorModel
import com.vsple.productdetailapplication.ui.models.ImageModel
import com.vsple.productdetailapplication.ui.models.SizeModel
import com.vsple.productdetailapplication.ui.utils.setSafeOnClickListener

class ProductInfoActivity : AppCompatActivity() {

    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivityProductInfoBinding
    private var sizeListAdapter: SizeListAdapter? = null
    private var colorListAdapter: ColorListAdapter? = null
    private var dialog: Dialog? = null
    var selectedItemPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPosterPagerAdapter()



        binding.tvSizeTemp.setSafeOnClickListener {

            binding.editText.isFocusable = true
            if (binding.editText.text.toString() != null) {
                selectedItemPos =
                    sizeDateList().indexOfFirst { it.title == binding.editText.text.toString() }
            }
            bottomDialogSize(selectedItemPos)
        }

        binding.editText.setSafeOnClickListener {

            binding.editText.isFocusable = true
            if (binding.editText.text.toString() != null) {
                selectedItemPos =
                    sizeDateList().indexOfFirst { it.title == binding.editText.text.toString() }
            }
            bottomDialogSize(selectedItemPos)
        }



        binding.tvColorTemp.setSafeOnClickListener {

            if (binding.editText.text.toString() != null) {
                selectedItemPos =
                    colorDateList().indexOfFirst { it.name == binding.editText.text.toString() }
            }
            bottomDialogColor(selectedItemPos)
        }
        binding.editColor.setSafeOnClickListener {

            if (binding.editText.text.toString() != null) {
                selectedItemPos =
                    colorDateList().indexOfFirst { it.name == binding.editText.text.toString() }
            }
            bottomDialogColor(selectedItemPos)
        }


        binding.tvQuantityTemp.setSafeOnClickListener {
            bottomDialogQuantity()
        }
        binding.editQuantity.setSafeOnClickListener {
            bottomDialogQuantity()
        }


    }

    private fun setPosterPagerAdapter(

    ) {
        binding.spb.segmentCount = productImageList().size
        binding.viewPager.offscreenPageLimit = productImageList().size
        mViewPagerAdapter = ViewPagerAdapter(this, productImageList())
        binding.viewPager.adapter = mViewPagerAdapter
        binding.spb.viewPager = binding.viewPager
        binding.spb.start()
    }


    /** function for static image list for viewpager*/
    private fun productImageList(): List<ImageModel> {

        return listOf(
            ImageModel(
                1,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            ),

            ImageModel(
                2,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            ),

            ImageModel(
                3,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            ),

            ImageModel(
                4,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            ),

            ImageModel(
                6,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            ),

            ImageModel(
                6,
                this@ProductInfoActivity.getDrawable(R.drawable.imgdumy)!!,
            )

        )
    }

    private fun sizeDateList(): List<SizeModel> {

        return listOf(
            SizeModel(
                "Small",
                "₹ 1299",
                false
            ),

            SizeModel(
                "Medium",
                "₹ 1299",
                false
            ),

            SizeModel(
                "Large",
                "₹ 1299",
                true
            ),

            SizeModel(
                "" +
                        "Xtra Large",
                "₹ 1299",
                false
            ),

            SizeModel(
                "XXtra Large",
                "₹ 1299",
                false
            )

        )
    }

    private fun colorDateList(): List<ColorModel> {

        return listOf(
            ColorModel(
                "Green",
                this@ProductInfoActivity.getColor(R.color.greenLite)!!,

                ),

            ColorModel(
                "Gray",
                this@ProductInfoActivity.getColor(R.color.gray)!!,

                ),

            ColorModel(
                "Black",
                this@ProductInfoActivity.getColor(R.color.black000000)!!,

                ),


            )
    }


    /**
     * this function use for to show dialog on size selection
     * */
    private fun bottomDialogSize(selectedItemPos: Int) {

        var itemClickListener: SizeListAdapter.ItemClickListener
        var position = 0
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@ProductInfoActivity)
            val view =
                layoutInflater.inflate(R.layout.item_bottom_shhet_size, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.rvSize)
            val tvDone = view.findViewById<TextView>(R.id.tvDone)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            mBottomSheetDialog.setCancelable(false)
            val layoutManager = LinearLayoutManager(this@ProductInfoActivity)
            recyclerView.layoutManager = layoutManager

            itemClickListener = object : SizeListAdapter.ItemClickListener {
                override fun onClick(s: Int?) {
                    // Notify adapter
                    position = s!!
                    recyclerView.post(Runnable { sizeListAdapter?.notifyDataSetChanged() })

                }

                override fun onClickPosition(s: Int?) {

                }
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            sizeListAdapter = SizeListAdapter(
                this, sizeDateList(), selectedItemPos, itemClickListener
            )


            // set adapter
            recyclerView.adapter = sizeListAdapter
            mBottomSheetDialog.setContentView(view)
            tvDone.setOnClickListener {
                Log.d("TAG", "bottomDialogSize: " + position)
                binding.tvSizeTemp.visibility = View.GONE
                binding.tvSize.visibility = View.VISIBLE
                mBottomSheetDialog.dismiss()
                binding.editText.isFocusable = true
                if (position == -1) {
                    binding.tvSize.boxStrokeColor = Color.CYAN
                    binding.editText.setText(sizeDateList()[selectedItemPos].title.toString())
                } else {
                    binding.tvSize.boxStrokeColor = Color.BLUE
                    // binding.tvSize.boxStrokeColor =  getColor(R.color.greenLite)
                    //  binding.editText.background = getDrawable(R.drawable.bg_button)
                    binding.editText.setText(sizeDateList()[position].title.toString())
                }

            }

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun bottomDialogColor(selectedItemPos: Int) {

        var itemClickListener: ColorListAdapter.ItemClickListener
        var position = 0
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@ProductInfoActivity)
            val view =
                layoutInflater.inflate(R.layout.item_bottom_shhet_size, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.rvSize)
            val tvDone = view.findViewById<TextView>(R.id.tvDone)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            mBottomSheetDialog.setCancelable(false)
            val layoutManager = LinearLayoutManager(this@ProductInfoActivity)
            recyclerView.layoutManager = layoutManager

            itemClickListener = object : ColorListAdapter.ItemClickListener {
                override fun onClick(s: Int?) {
                    // Notify adapter
                    position = s!!
                    recyclerView.post(Runnable { colorListAdapter?.notifyDataSetChanged() })

                }

                override fun onClickPosition(s: Int?) {

                }
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            colorListAdapter = ColorListAdapter(
                this, colorDateList(), selectedItemPos, itemClickListener
            )


            // set adapter
            recyclerView.adapter = colorListAdapter
            mBottomSheetDialog.setContentView(view)
            tvDone.setOnClickListener {
                Log.d("TAG", "bottomDialogSize: " + position)
                mBottomSheetDialog.dismiss()

                binding.tvColorTemp.visibility = View.GONE
                binding.TIColor.visibility = View.VISIBLE

                if (position == -1) {
                    binding.editColor.setText(colorDateList()[selectedItemPos].name)
                } else {
                    binding.editColor.setText(colorDateList()[position].name.toString())
                }

            }

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun bottomDialogQuantity() {

        var count = 0
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@ProductInfoActivity)
            val view =
                layoutInflater.inflate(R.layout.item_qunatity, null)
            val imgMinus = view.findViewById<ImageView>(R.id.imgMinus)
            val imgPlus = view.findViewById<ImageView>(R.id.imgPlus)
            val tvDone = view.findViewById<TextView>(R.id.tvDone)
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            mBottomSheetDialog.setCancelable(false)



            mBottomSheetDialog.setContentView(view)
            imgPlus.setOnClickListener {


                count++
                tvTitle.text = count.toString()

            }
            imgMinus.setOnClickListener {
                if (count > 0) {
                    count--
                    tvTitle.text = count.toString()
                }


            }
            tvDone.setOnClickListener {

                binding.TIQuantity.visibility = View.VISIBLE
                binding.tvQuantityTemp.visibility = View.GONE

                mBottomSheetDialog.dismiss()
                binding.editQuantity.setText(count.toString())

            }

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}