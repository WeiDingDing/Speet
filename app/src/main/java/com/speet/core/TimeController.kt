package com.speet.core

object TimeController {
    private var speed = 1.0f
    private var baseTime = System.currentTimeMillis()
    private var baseNanoTime = System.nanoTime()

    fun setSpeed(newSpeed: Float) {
        speed = newSpeed
        baseTime = System.currentTimeMillis()
        baseNanoTime = System.nanoTime()
    }

    fun getSpeed(): Float = speed

    fun getModifiedTime(currentTime: Long): Long {
        val timeDiff = currentTime - baseTime
        return baseTime + (timeDiff * speed).toLong()
    }

    fun getModifiedNanoTime(currentNanoTime: Long): Long {
        val nanoTimeDiff = currentNanoTime - baseNanoTime
        return baseNanoTime + (nanoTimeDiff * speed).toLong()
    }
} 