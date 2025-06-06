package com.speet.ui

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.WindowManager
import com.speet.core.TimeController

class FloatingService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var speedControlView: SpeedControlView

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        speedControlView = SpeedControlView(this)
        
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            android.graphics.PixelFormat.TRANSLUCENT
        )

        windowManager.addView(speedControlView, params)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        if (::speedControlView.isInitialized) {
            windowManager.removeView(speedControlView)
        }
    }
} 