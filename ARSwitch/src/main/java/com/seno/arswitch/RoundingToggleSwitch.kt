package com.seno.arswitch

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.seno.arswitch.databinding.ViewRoundingToggleSwitchBinding
import com.seno.arswitch.extensions.dpToPx

class RoundingToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    var onSwitchToggleListener: OnSwitchToggleListener? = null

    var currentPosition: SwitchPosition = SwitchPosition.LEFT
    set(value) {
        if (field == value) {
            return
        }

        if (value == SwitchPosition.LEFT) {
            binding.ivLeft.setColorFilter(selectedTintColor)
            binding.ivRight.setColorFilter(unSelectedTintColor)
        } else {
            binding.ivLeft.setColorFilter(unSelectedTintColor)
            binding.ivRight.setColorFilter(selectedTintColor)
        }

        field = value
    }

    var indicatorColor: Int = context.getColor(R.color.color_FFFFFFFF)
    set(value) {
        binding.ivIndicator.background = drawCircle(color = value)
        field = value
    }

    var sideMargin: Float = 10f
        set(value) {

            val leftParams = (binding.ivLeft.layoutParams as ConstraintLayout.LayoutParams).apply {
                setMargins(value.dpToPx(resources = resources), 0, 0, 0)
            }
            binding.ivLeft.layoutParams = leftParams

            val rightParams = (binding.ivRight.layoutParams as ConstraintLayout.LayoutParams).apply {
                setMargins(0, 0, value.dpToPx(resources = resources), 0)
            }
            binding.ivRight.layoutParams = rightParams
            field = value
        }

    var selectedTintColor: Int = context.getColor(R.color.color_FF000000)
    set(value) {
        if (currentPosition == SwitchPosition.LEFT) {
            binding.ivLeft.setColorFilter(value)
        } else {
            binding.ivRight.setColorFilter(value)
        }
      field = value
    }
    var unSelectedTintColor: Int = context.getColor(R.color.color_FFADB5BD)
        set(value) {
            if (currentPosition == SwitchPosition.LEFT) {
                binding.ivRight.setColorFilter(value)
            } else {
                binding.ivLeft.setColorFilter(value)
            }
            field = value
        }

    var duration: Long = 100
    var activeAnimation = true

    private var binding: ViewRoundingToggleSwitchBinding = ViewRoundingToggleSwitchBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        binding.switchView = this@RoundingToggleSwitch
        setInitialUI()
    }

    @DrawableRes
    var leftImageResource: Int = 0
        set(value) {
        binding.ivLeft.setImageResource(value)
        field = value
    }

    @DrawableRes
    var rightImageResource: Int = 0
        set(value) {
            binding.ivRight.setImageResource(value)
            field = value
        }

    @SuppressLint("ResourceType")
    private fun setInitialUI() {
        setBackground()
        sideMargin = 10f
        setIndicator()
    }

    @SuppressLint("ResourceType")
    private fun setIndicator(@ColorRes color: Int = context.getColor(R.color.color_FFFFFFFF)) {
        binding.ivIndicator.background = drawCircle(color = color)
    }

    @SuppressLint("ResourceAsColor")
    private fun setBackground(
        @SuppressLint("ResourceType") @ColorRes color: Int = context.getColor(R.color.color_FFADB5BD),
        backgroundRadius: Float = 50f
    ) {
        val drawable = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    backgroundRadius, backgroundRadius,
                    backgroundRadius, backgroundRadius,
                    backgroundRadius, backgroundRadius,
                    backgroundRadius, backgroundRadius),
                null,
                null
            )
        ).apply {
            this.intrinsicHeight = 50
            this.intrinsicWidth = 50
            paint.color = color
        }
        binding.clRoot.background = drawable
    }

    fun clickRoundingSwitch() {
        moveIndicator()
        currentPosition = if (currentPosition == SwitchPosition.LEFT) {
            SwitchPosition.RIGHT
        } else {
            SwitchPosition.LEFT
        }
        onSwitchToggleListener?.onToggled(currentPosition)
    }

    private fun moveIndicator() {
        if (activeAnimation) {
            if (currentPosition == SwitchPosition.LEFT) {
                binding.ivIndicator.animate()
                    .translationX(binding.ivRight.x - sideMargin.dpToPx(resources = resources))
                    .duration = this@RoundingToggleSwitch.duration
            } else {
                binding.ivIndicator.animate()
                    .translationX(0f)
                    .duration = this@RoundingToggleSwitch.duration
            }
        } else {
            if (currentPosition == SwitchPosition.LEFT) {
                binding.ivIndicator.translationX = binding.ivRight.x - sideMargin.dpToPx(resources = resources)
            } else {
                binding.ivIndicator.translationX = 0f
            }
        }
    }

    private fun drawCircle(color: Int): ShapeDrawable {
        return ShapeDrawable(OvalShape()).apply {
            this.intrinsicHeight = 50
            this.intrinsicWidth = 50
            paint.color = color
        }
    }

    interface OnSwitchToggleListener {
        fun onToggled(position: SwitchPosition)
    }

    enum class SwitchPosition {
        LEFT, RIGHT
    }
}
