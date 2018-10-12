package com.yizhipin.goods

import android.widget.EditText
import com.yizhipin.base.widgets.NumberButton
import org.jetbrains.anko.find

/*
    三方控件扩展
 */
fun NumberButton.getEditText(): EditText {
    return find(R.id.text_count)
}
