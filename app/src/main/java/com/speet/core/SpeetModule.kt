package com.speet.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class SpeetModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == "android") {
            hookSystemTime()
        }
    }

    private fun hookSystemTime() {
        // Hook System.currentTimeMillis
        XposedHelpers.findAndHookMethod(
            System::class.java,
            "currentTimeMillis",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    param.result = TimeController.getModifiedTime(System.currentTimeMillis())
                }
            }
        )

        // Hook System.nanoTime
        XposedHelpers.findAndHookMethod(
            System::class.java,
            "nanoTime",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    param.result = TimeController.getModifiedNanoTime(System.nanoTime())
                }
            }
        )
    }
} 