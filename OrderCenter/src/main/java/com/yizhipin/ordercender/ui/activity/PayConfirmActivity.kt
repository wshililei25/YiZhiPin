package com.yizhipin.ordercender.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.widgets.PayRadioGroup
import com.yizhipin.base.widgets.PayRadioPurified
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.presenter.OrderConfirmPresenter
import kotlinx.android.synthetic.main.activity_pay_confirm.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 支付确认
 */

class PayConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), View.OnClickListener {

    /* @Autowired(name = BaseConstant.KEY_IS_PIN) //注解接收上个页面的传参
     @JvmField
     var mIsPin: Boolean = false
     @Autowired(name = BaseConstant.KEY_GOODS_LIST) //注解接收上个页面的传参
     @JvmField
     var mGoodsList: ArrayList<Goods>? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_confirm)

        initView()
        loadData()
    }

    private fun initView() {
        mPayRadioGroup.setOnCheckedChangeListener(object :PayRadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: PayRadioGroup, checkedId: Int) {
                for (i in 0 until group.getChildCount()) {
                    (group.getChildAt(i) as PayRadioPurified).setChangeImg(checkedId)
                }
            }

        })
    }

    private fun loadData() {

    }

    override fun injectComponent() {
//        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
//        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {


        }
    }


}