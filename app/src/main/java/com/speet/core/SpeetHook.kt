package com.speet.core

import com.highcapable.yukihookapi.annotation.XposedInit
import com.highcapable.yukihookapi.hook.xposed.YukiHookXposedInit
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@XposedInit(
    packageName = "com.speet",
    entryClassName = "com.speet.core.SpeetHook",
    description = "游戏变速器",
    minVersion = 82,
    scope = ["android"]
)
class SpeetHook : IYukiHookXposedInit {
    override fun onInit() = YukiHookXposedInit.init {
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