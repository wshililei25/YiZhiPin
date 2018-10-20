package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.widgets.PayPasswordDialog
import com.yizhipin.base.widgets.PayRadioGroup
import com.yizhipin.base.widgets.PayRadioPurified
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.PayConfirmPresenter
import com.yizhipin.ordercender.presenter.PayConfirmView
import com.yizhipin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_pay_confirm.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 支付确认
 */

class PayConfirmActivity : BaseMvpActivity<PayConfirmPresenter>(), PayConfirmView, View.OnClickListener {

    @Autowired(name = BaseConstant.KEY_IS_PIN) //注解接收上个页面的传参
    @JvmField
    var mIsPin: Boolean = false
    @Autowired(name = OrderConstant.KEY_ADDRESS_ID) //注解接收上个页面的传参
    @JvmField
    var mAddressId: Int = 0

    var mGoodsList: MutableList<Goods>? = null
    var mGoodsId = "" //商品id
    var mProductCounts = "" //商品数量
    var mConponId = "" //优惠券id
    var mType = "balance" //支付方式

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_confirm)

        initView()
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mGoodsList = intent.getParcelableArrayListExtra<Goods>(OrderConstant.KEY_GOODS_LIST)

        var amount = 0.00
        if (mIsPin) {
            for (good in mGoodsList as MutableList<Goods>) {
                amount += good.pinPrice * good.goodsCount
            }
        } else {
            for (good in mGoodsList as MutableList<Goods>) {
                amount += good.price * good.goodsCount
            }
        }
        for (list in mGoodsList!!) {
            mGoodsId += list.id.toString().plus(",")
            mProductCounts += list.goodsCount.toString().plus(",")
        }
        mPostageTv.text = getString(R.string.rmb).plus(amount.toString())
        mPaymentTv.text = getString(R.string.rmb).plus(amount.toString())

        if (AppPrefsUtils.getString(ProviderConstant.KEY_AMOUNT).toDouble() < amount) {
            mBalanceRadio.setTextDesc(getString(R.string.balance_insufficient))
        }


        mBalanceRadio.isChecked
        mPayRadioGroup.setOnCheckedChangeListener(object : PayRadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: PayRadioGroup, checkedId: Int) {
                for (i in 0 until group.getChildCount()) {
                    (group.getChildAt(i) as PayRadioPurified).setChangeImg(checkedId)
                }
                if (mBalanceRadio.isChecked) {
                    mType = "balance"
                }
                if (mAliRadio.isChecked) {
                    mType = "Alipay"
                }
                if (mWechatRadio.isChecked) {
                    mType = "Weixin"
                }
            }

        })

        mPayBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPayBtn -> {

                //暂时注释
                /*if (AppPrefsUtils.getString(ProviderConstant.KEY_PAY_PWD).isNullOrEmpty()) {
                    toast("请先设置支付密码")
                    return
                }*/

                var map = mutableMapOf<String, String>()
                map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                map.put("conponId", mConponId)
                map.put("pids", mGoodsId)
                map.put("productCounts", mProductCounts)
                map.put("addressId", mAddressId.toString())
                map.put("payType", mType)

                mBasePresenter.submitOrder(map)
            }
        }
    }

    override fun onSubmitOrderSuccess(result: Boolean) {
        val dialog = PayPasswordDialog(this, R.style.PayDialog)
        dialog.setDialogClick(object : PayPasswordDialog.DialogClick {
            override fun doConfirm(password: String?) {
                dialog.dismiss()
            }
        })
        dialog.show()
    }

}