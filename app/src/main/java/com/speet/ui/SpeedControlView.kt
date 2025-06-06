package com.speet.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.speet.R
import com.speet.core.TimeController

class SpeedControlView(context: Context) : FrameLayout(context) {
    private var initialX: Int = 0
    private var initialY: Int = 0
    private var initialTouchX: Float = 0f
    private var initialTouchY: Float = 0f
    private lateinit var speedText: TextView
    private lateinit var speedSeekBar: SeekBar

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_speed_control, this, true)
        initViews()
        setupListeners()
    }

    private fun initViews() {
        speedText = findViewById(R.id.speedText)
        speedSeekBar = findViewById(R.id.speedSeekBar)
        speedSeekBar.progress = ((TimeController.getSpeed() - 0.5f) * 100).toInt()
        updateSpeedText()
    }

    private fun setupListeners() {
        speedSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = 0.5f + (progress / 100f)
                TimeController.setSpeed(speed)
                updateSpeedText()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = (parent as View).x.toInt()
                    initialY = (parent as View).y.toInt()
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = initialX + (event.rawX - initialTouchX).toInt()
                    val y = initialY + (event.rawY - initialTouchY).toInt()
                    (parent as View).x = x.toFloat()
                    (parent as View).y = y.toFloat()
                    true
                }
                else -> false
            }
        }
    }

    private fun updateSpeedText() {
        speedText.text = String.format("%.1fx", TimeController.getSpeed())
    }
} 