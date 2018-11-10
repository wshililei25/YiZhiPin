package com.yizhipin.ordercender.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.StringUtils
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.ShipAddress
import com.yizhipin.ordercender.event.SelectAddressEvent
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.OrderConfirmPresenter
import com.yizhipin.ordercender.presenter.view.OrderConfirmView
import com.yizhipin.ordercender.ui.adapter.OrderConfirmAdapter
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 订单确认
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView, View.OnClickListener {

    @Autowired(name = BaseConstant.KEY_IS_PIN) //注解接收上个页面的传参
    @JvmField
    var mIsPin: Boolean = false
    @Autowired(name = BaseConstant.KEY_GOODS_LIST) //注解接收上个页面的传参
    @JvmField
    var mGoodsList: ArrayList<Goods>? = null

    private var mShipAddress: ShipAddress? = null
    private lateinit var mOrderConfirmAdapter: OrderConfirmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
        initObserve()
    }

    private fun initView() {

        mOrderGoodsRv.layoutManager = LinearLayoutManager(this!!)
        mOrderConfirmAdapter = OrderConfirmAdapter(this, mIsPin)
        mOrderConfirmAdapter.setData(mGoodsList as MutableList<Goods>)
        mOrderGoodsRv.adapter = mOrderConfirmAdapter

        if (mIsPin) {
            /* var amount = 0.00
             for (good in mGoodsList as MutableList<Goods>) {
                 amount += good.pinPrice
             }
             mAmountTv.text = getString(R.string.rmb).plus(amount.toString())*/
            mBtn.text = getString(R.string.participate_share_bill)
        } else {
            /* var amount = 0.00
             for (good in mGoodsList as MutableList<Goods>) {
                 amount += good.price
             }
             mAmountTv.text = getString(R.string.rmb).plus(amount.toString())*/
            mBtn.text = getString(R.string.commit_order)
        }

        /* var amount = 0.00
         for (good in mGoodsList as MutableList<Goods>) {
             amount += good.postage
         }
         mPostageTv.text = getString(R.string.rmb).plus(amount.toString())*/
        mBtn.onClick(this)
        mShipView.onClick(this)
        mSelectShipTv.onClick(this)

//               if (mOrderId == 0) mOrderView.setVisible(false) else mOrderView.setVisible(true)
    }

    private fun loadData() {

        if (mGoodsList!!.get(0).primaryCategory == "homestay") { //一品小住
            mShipView.isEnabled = false
            mSelectShipTv.setVisible(false)
            mShipView.setVisible(true)
            mShipNameTv.text = getString(R.string.check_in_person) + AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NICKNAME)
            mShipMobileTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
            mShipAddressTv.setVisible(false)
        } else { //其他
            var map = mutableMapOf<String, String>()
            map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
            mBasePresenter.getDefaultAddress(map)
        }
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取默认地址成功
     */
    override fun onGetDefaultAddressSuccess(result: ShipAddress) {

        updateAddressView(result)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSelectShipTv, R.id.mShipView -> {
                startActivity<ShipAddressActivity>()
            }
            R.id.mBtn -> {
                if (mGoodsList!!.get(0).primaryCategory != "homestay" && null == mShipAddress) {
                    toast("请选择收货地址")
                    return
                }

                startActivityForResult<PayConfirmActivity>(ProvideReqCode.CODE_REQ_PAY, OrderConstant.KEY_GOODS_LIST to mGoodsList
                        , BaseConstant.KEY_IS_PIN to mIsPin, OrderConstant.KEY_ADDRESS_ID to if (mShipAddress == null) "" else mShipAddress!!.id)
            }
        }
    }

    /**
     * 选择地址回调
     */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { t: SelectAddressEvent ->
                    run {
                        updateAddressView(t.address)
                    }
                }.registerInBus(this)
    }

    private fun updateAddressView(result: ShipAddress) {

        mShipAddress = result
        if (result == null) {
            mSelectShipTv.setVisible(true)
            mShipView.setVisible(false)
        } else {
            mSelectShipTv.setVisible(false)
            mShipView.setVisible(true)

            mShipNameTv.text = getString(R.string.consignee).plus(result.name)
            mShipMobileTv.text = StringUtils.setMobileStar(result.mobile)
            mShipAddressTv.text = result.pro + result.city + result.area + result.detail
        }
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