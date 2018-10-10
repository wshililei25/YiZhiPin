package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.event.SelectAddressEvent
import com.yizhipin.ordercender.injection.component.DaggerShipAddressComponent
import com.yizhipin.ordercender.injection.module.ShipAddressModule
import com.yizhipin.ordercender.presenter.ShipAddressPresenter
import com.yizhipin.ordercender.presenter.view.ShipAddressView
import com.yizhipin.ordercender.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/24.
 */
class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView, View.OnClickListener {

    private lateinit var mShipAddressAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    private fun initView() {
        mHeaderBar.getRightTv().onClick(this)

        mAddressRv.layoutManager = LinearLayoutManager(this)
        mShipAddressAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mShipAddressAdapter
        mShipAddressAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress> {
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }

        })

        //设置操作事件
        mShipAddressAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {

            override fun onSetDefault(address: ShipAddress) {
                var map = mutableMapOf<String, String>()
                map.put("id", address.id.toString())
                mBasePresenter.setDefaultShipAddress(map)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "确定删除该地址？", "取消", null, arrayOf("确定"), this@ShipAddressActivity, AlertView.Style.Alert, OnItemClickListener { o, position ->
                    if (position == 0) {
                        var map = mutableMapOf<String, String>()
                        map.put("id", address.id.toString())
                        mBasePresenter.deleteShipAddress(map)
                    }
                }
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mBasePresenter.getShipAddressList()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(mActivityComponent).shipAddressModule(ShipAddressModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRightTv -> {
                startActivity<ShipAddressEditActivity>("size" to mShipAddressAdapter.dataList.size)
            }
        }
    }

    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mShipAddressAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onSetDefaultResult(result: Boolean) {
        loadData()
    }

    override fun onDeleteResult(result: Boolean) {
        loadData()
    }
}