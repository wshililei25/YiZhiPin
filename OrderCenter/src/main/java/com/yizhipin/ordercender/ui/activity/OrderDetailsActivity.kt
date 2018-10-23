package com.yizhipin.ordercender.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.common.BaseApplication.Companion.context
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.data.response.Order
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.OrderDetailPresenter
import com.yizhipin.ordercender.presenter.view.OrderDetailView
import com.yizhipin.ordercender.ui.adapter.OrderDetailsAdapter
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_details.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 订单详情
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_DETAILS)
class OrderDetailsActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView, View.OnClickListener {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID) //注解接收上个页面的传参
    @JvmField
    var mOrderId: String = ""

    private lateinit var mOrderDetailsAdapter: OrderDetailsAdapter
    private var mOrder: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        initView()
        loadData()
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mOrderDetailsAdapter = OrderDetailsAdapter(this)
        mOrderGoodsRv.adapter = mOrderDetailsAdapter

    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mOrderId)
        mBasePresenter.getOrderById(map)
    }

    /**
     * 获取订单详情成功
     */
    override fun onGetOrderByIdResult(result: Order) {
        mOrder = result

        with(result) {
            mOrderDetailsAdapter.setData(products)
            mShipNameTv.text = "收货人:  ${receiveName}"
            mShipMobileTv.text = StringUtils.setMobileStar(receiveMobile)
            mShipAddressTv.text = receivePro.plus(receiveCity).plus(receiveArea).plus(receiveDetail)
            mPostageTv.text = "邮费:  ${postage}"
            mAmountTv.text = "实际支付:  ${payAmount}"
            mOrderNumberTv.text = orderNum
            mCreateTimeTv.text = createTime
            mRealityPayTv.text = payAmount
//            mStaleTimeTv.text = payAmount

            when (orderType) {
                "buy" -> mTypeTv.text = context.getString(R.string.order_original)
                "tuan" -> mTypeTv.text = context.getString(R.string.order_pin_tuan)
                "bai" -> mTypeTv.text = context.getString(R.string.order_pin_tuan)
            }

            when (status.toInt()) {
                1, 9 -> {
                    mOrderTypeTv.text = getString(R.string.for_paymen)
                    mBtn.text = getString(R.string.go_pay)
                    mBtn.setVisible(true)
                }
                2 -> mOrderTypeTv.text = getString(R.string.send_goods)
                3, 10, 11 -> {
                    mOrderTypeTv.text = getString(R.string.take_goods)
                    mBtn.text = getString(R.string.confirm_receipt)
                    mBtn.setVisible(true)
                }
                4 -> {
                    mOrderTypeTv.text = getString(R.string.appraise)
                    mBtn.text = getString(R.string.go_evaluate)
                    mBtn.setVisible(true)
                }
//                5 -> mOrderTypeTv.
                6 -> mOrderTypeTv.text = getString(R.string.customer_serviceing)
//                7 -> mOrderTypeTv.text = getString(R.string.for_paymen)
//                8 -> mOrderTypeTv.text = getString(R.string.for_paymen)
            }
        }
    }

    override fun onClick(v: View) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            ProvideReqCode.CODE_RESULT_PAY -> finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}