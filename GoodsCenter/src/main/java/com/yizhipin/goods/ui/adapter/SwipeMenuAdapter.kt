package com.yizhipin.goods.ui.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import com.yizhipin.goods.R
import com.yizhipin.goods.data.response.CartGoods
import com.yizhipin.goods.ui.holder.SuperViewHolder
import com.yizhipin.goods.widget.SwipeMenuView

class SwipeMenuAdapter(context: Context) : ListBaseAdapter<CartGoods>(context) {

    private var mOnSwipeListener: onSwipeListener? = null

    override fun getLayoutId(): Int {
        return R.layout.layout_cart_goods_item
    }

    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        val contentView = holder.getView<View>(R.id.swipe_content)
        val title = holder.getView<TextView>(R.id.title)
        val btnDelete = holder.getView(R.id.btnDelete)
        val btnUnRead = holder.getView(R.id.btnUnRead)
        val btnTop = holder.getView(R.id.btnTop)

        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        (holder.itemView as SwipeMenuView).setIos(false).setLeftSwipe(if (position % 2 == 0) true else false)

        title.setText(dataList[position].shopName + if (position % 2 == 0) "我只能右滑动" else "我只能左滑动")

        //隐藏控件
        btnUnRead.setVisibility(if (position % 3 == 0) View.GONE else View.VISIBLE)

        btnDelete.setOnClickListener(View.OnClickListener {
            if (null != mOnSwipeListener) {
                //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                //((CstSwipeDelMenu) holder.itemView).quickClose();
                mOnSwipeListener!!.onDel(position)
            }
        })
        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
        contentView.setOnClickListener { v ->
//            Toast.makeShortToast(mContext, dataList[position].shopName)
            Log.d("TAG", "onClick() called with: v = [$v]")
        }
        //置顶：
        btnTop.setOnClickListener(View.OnClickListener {
            if (null != mOnSwipeListener) {
                mOnSwipeListener!!.onTop(position)
            }
        })
    }

    /**
     * 和Activity通信的接口
     */
    interface onSwipeListener {
        fun onDel(pos: Int)

        fun onTop(pos: Int)
    }

    fun setOnDelListener(mOnDelListener: onSwipeListener) {
        this.mOnSwipeListener = mOnDelListener
    }


}

