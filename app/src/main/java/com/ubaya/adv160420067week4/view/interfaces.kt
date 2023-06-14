package com.ubaya.adv160420067week4.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.adv160420067week4.model.Student

interface StudentItemLayoutInterface{
    fun onButtonDetailClick(v: View)
}
interface StudentNotification{
    fun onNotification(v:View)
}