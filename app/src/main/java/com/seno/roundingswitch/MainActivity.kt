package com.seno.roundingswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.seno.arswitch.RoundingToggleSwitch
import com.seno.roundingswitch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding?.roundingSwitch?.apply {
            duration = 100
            activeAnimation = true
            leftImageResource = R.drawable.ic_alarm_on
            rightImageResource = R.drawable.ic_alarm_off
            indicatorColor = getColor(R.color.teal_200)
            selectedTintColor = getColor(R.color.purple_500)
            unSelectedTintColor = getColor(R.color.purple_200)
            sideMargin = 10f
            onSwitchToggleListener = object: RoundingToggleSwitch.OnSwitchToggleListener {
                override fun onToggled(position: RoundingToggleSwitch.SwitchPosition) {
                    // do something...
                }
            }
        }
    }
}