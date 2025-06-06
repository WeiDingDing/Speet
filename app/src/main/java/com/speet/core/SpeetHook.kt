package com.speet.core

import com.highcapable.yukihookapi.hook.xposed.YukiHookXposedInit
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

class SpeetHook : IYukiHookXposedInit {
    override fun onInit() = YukiHookXposedInit.init {
        // 设置模块包名
        appPackageName = "com.speet"
        // 设置 Hook 作用域
        hookPackageName = "android"
        // 设置 Hook 入口
        hookEntry {
            // Hook System.currentTimeMillis
            findClass("java.lang.System").hook {
                injectMember {
                    method {
                        name = "currentTimeMillis"
                        returnType = Long::class.java
                    }
                    beforeHook {
                        result = TimeController.getModifiedTime(System.currentTimeMillis())
                    }
                }
            }
            
            // Hook System.nanoTime
            findClass("java.lang.System").hook {
                injectMember {
                    method {
                        name = "nanoTime"
                        returnType = Long::class.java
                    }
                    beforeHook {
                        result = TimeController.getModifiedNanoTime(System.nanoTime())
                    }
                }
            }
        }
    }
} 