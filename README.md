# Speet

作者：Vitity

本项目为 Android 示例应用，支持 GitHub Actions 自动化编译 APK。

## 自动化构建

推送代码到 main 分支后，GitHub Actions 会自动编译并上传 APK 到构建产物。

## 目录结构

- app/ 应用主模块
- gradle/ Gradle Wrapper
- .github/workflows/ 自动化工作流

## 游戏变速器

一个基于 Xposed 框架的游戏变速工具，支持 1-20 倍速调节。

## 功能特点

- 支持 1-20 倍速调节
- 悬浮窗控制
- 实时速度显示
- 支持 Android 7.0 及以上版本

## 使用说明

1. 安装 Xposed 框架
2. 安装本应用
3. 在 Xposed 管理器中启用模块
4. 重启设备
5. 启动目标应用，悬浮窗会自动显示
6. 通过拖动滑块调节速度

## 构建状态

[![Android Build](https://github.com/yourusername/Speet/actions/workflows/android-build.yml/badge.svg)](https://github.com/yourusername/Speet/actions/workflows/android-build.yml)

## 下载

从 [Releases](https://github.com/yourusername/Speet/releases) 页面下载最新版本。

## 注意事项

- 需要 root 权限
- 需要 Xposed 框架
- 部分应用可能无法正常工作
- 使用前请备份重要数据
