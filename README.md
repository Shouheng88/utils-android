![Banner](https://github.com/CostCost/Resources/blob/master/github/xbanner.jpg?raw=true)

<h1 align="center">Android-Utils: 常用的 Android 工具库合集</h1>

<p align="center">
  <a href="http://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/hexpm/l/plug.svg" alt="License" />
  </a>
  <a href="https://bintray.com/beta/#/easymark/Android/utils-core?tab=overview">
    <img src="https://img.shields.io/maven-metadata/v/https/dl.bintray.com/easymark/Android/me/shouheng/utils/utils-core/maven-metadata.xml.svg" alt="Version" />
  </a>
  <a href="https://www.codacy.com/manual/Shouheng88/Android-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Shouheng88/Android-utils&amp;utm_campaign=Badge_Grade">
    <img src="https://api.codacy.com/project/badge/Grade/58b18f9bf47543cbbaf4ca67bcadfc7b" alt="Code Grade"/>
  </a>
  <a href="https://travis-ci.org/Shouheng88/Android-utils">
    <img src="https://travis-ci.org/Shouheng88/Android-utils.svg?branch=master" alt="Build"/>
  </a>
    <a href="https://developer.android.com/about/versions/android-4.0.html">
    <img src="https://img.shields.io/badge/API-14%2B-blue.svg?style=flat-square" alt="Min Sdk Version" />
  </a>
   <a href="https://github.com/Shouheng88">
    <img src="https://img.shields.io/badge/Author-CodeBrick-orange.svg?style=flat-square" alt="Author" />
  </a>
  <a target="_blank" href="https://shang.qq.com/wpa/qunwpa?idkey=2711a5fa2e3ecfbaae34bd2cf2c98a5b25dd7d5cc56a3928abee84ae7a984253">
    <img src="https://img.shields.io/badge/QQ%E7%BE%A4-1018235573-orange.svg?style=flat-square" alt="QQ Group" />
  </a>
</P>

## 1、关于项目

该项目旨在收集 Android 开发中频繁使用的工具类，比如日志、Crash 收集、资源和属性获取、权限申请、TOAST 等，通过对相关的工具类进行封装，提供相关类的标准化应用，来降低开发者开发的难度。你可以参考 README 来了解项目中包含的工具类库。

## 2、在项目中使用

### 2.1 添加工程依赖

该项目已经被提交到了 jCenter，所以你可以很方便地在你的项目中使用它。

首先，你需要引用 jCenter 到你的项目中：

```gradle
repositories { jcenter() }
```

然后，在你的项目中添加该依赖：

```gradle
implementation 'me.shouheng.utils:utils-core:latest-version'
```

如果需要使用 utils-ktx，可以在项目中添加如下依赖，

```gradle
implementation "me.shouheng.utils:utils-ktx:$latest-version"
```

### 2.2 项目初始化

然后你的自定义的 Application 中对工具类库进行初始化。这个步骤主要用来在类库内获取一个全局的 Context，从而使方法的调用更加简洁，不会占用你宝贵的启动时间。

```java
public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 对工具类库进行初始化
        UtilsApp.init(this);
    }
}
```

初始化完毕之后，我们可以对类库中包含对模块进行个性化定制。具体可以参考示例代码和源码。

### 2.2 该类库中目前提供的功能

按照关注的主题，我们将项目中的类按照主题分成了几个大类。目前项目中的类的结构如下：

|编号|类名|群组|说明|
|:-:|:-:|:-:|:-:|
|1|[ActivityUtils](./utils/src/main/java/me/shouheng/utils/app/ActivityUtils.java)|应用|Activity 启动、关闭、动画、启动参数构建|
|2|[AppUtils](./utils/src/main/java/me/shouheng/utils/app/AppUtils.java)|应用|App 安装与卸载，获取应用信息等
|3|[IntentUtils](./utils/src/main/java/me/shouheng/utils/app/IntentUtils.java)|应用|各种比较通用的意图，比如分享文字、打开应用商店等
|4|[ResUtils](./utils/src/main/java/me/shouheng/utils/app/ResUtils.java)|资源|图片、字符串和其他资源获取
|5|[EncodeUtils](./utils/src/main/java/me/shouheng/utils/data/EncodeUtils.java)|编码|base64 和 url 编码等
|6|[EncryptUtils](./utils/src/main/java/me/shouheng/utils/data/EncryptUtils.java)|加密|md5 sha256 加密方式
|7|[RegexUtils](./utils/src/main/java/me/shouheng/utils/data/RegexUtils.java)|数据处理|正则表达式
|8|[StringUtils](./utils/src/main/java/me/shouheng/utils/data/StringUtils.java)|数据处理|字符串处理
|9|[TimeUtils](./utils/src/main/java/me/shouheng/utils/data/TimeUtils.java)|数据处理|时间处理，时间和日期格式化等
|10|[DeviceUtils](./utils/src/main/java/me/shouheng/utils/device/DeviceUtils.java)|设备信息|设备信息，比如 imei 手机型号 生产商等
|11|[NetworkUtils](./utils/src/main/java/me/shouheng/utils/device/NetworkUtils.java)|设备信息|网络信息，网络类型等
|12|[ShellUtils](./utils/src/main/java/me/shouheng/utils/device/ShellUtils.java)|设备信息|命令行执行
|13|[PermissionUtils](./utils/src/main/java/me/shouheng/utils/permission/PermissionUtils.java)|权限|Android 6.0 运行时权限权限请求
|14|[CrashHelper](./utils/src/main/java/me/shouheng/utils/stability/CrashHelper.java)|稳定性|崩溃日志
|15|[L](./utils/src/main/java/me/shouheng/utils/stability/L.java)|稳定性|日志输出，日志格式化，日志输出到文档等
|16|[FileUtils](./utils/src/main/java/me/shouheng/utils/store/FileUtils.java)|数据存储|文件相关，获取文件信息，文件移动，删除和遍历等
|17|[PathUtils](./utils/src/main/java/me/shouheng/utils/store/PathUtils.java)|数据存储|文件路径，获取手机中各个文件夹的路径等
|18|[IOUtils](./utils/src/main/java/me/shouheng/utils/store/IOUtils.java)|数据存储|磁盘读写|
|19|[SPUtils](./utils/src/main/java/me/shouheng/utils/store/SPUtils.java)|数据存储|Sharedpreference 读写相关|
|20|[ImageUtils](./utils/src/main/java/me/shouheng/utils/ui/ImageUtils.java)|ui|图片处理，缩放，旋转，变形，加水印等|
|21|[ToastUtils](./utils/src/main/java/me/shouheng/utils/ui/ToastUtils.java)|ui|土司封装|
|22|[ViewUtils](./utils/src/main/java/me/shouheng/utils/ui/ViewUtils.java)|ui|获取控件信息以及软键盘操作等|

### 2.3 使用 Utils-ktx

`utils-ktx` 是基于 Android-Utils 和 Kotlin 的特性定制的拓展库，可以参考 utlis-ktx 模块来查看其源代码。其主要设计被用来简化常见的调用方式，从而使代码更加简洁。比如，

```kotlin
fun Context.attrFloatOf(attrRes: Int): Float = ResUtils.getAttrFloatValue(this, attrRes)

fun Context.attrColorOf(attrRes: Int): Int = ResUtils.getAttrColor(this, attrRes)

// ...

@ColorInt fun colorOf(@ColorRes id: Int): Int = ResUtils.getColor(id)

fun stringOf(@StringRes id: Int): String = ResUtils.getString(id)

// ...

fun String.md5(salt: String): String = EncryptUtils.md5(this, salt)

fun String.sha1(): String = EncryptUtils.sha1(this)

fun String.sha224(): String = EncryptUtils.sha224(this)

// ...
```

## 3、关于

### 3.1 关于作者

你可以通过访问下面的链接来获取作者的信息：

1. Twitter: https://twitter.com/shouheng_wang
2. Github: https://github.com/Shouheng88
3. 掘金：https://juejin.im/user/585555e11b69e6006c907a2a
4. 简书: https://www.jianshu.com/u/e1ad842673e2

### 3.2 更新日志

[更新日志](CHANGELOG.md)

## 4、捐赠项目

我们致力于为广大开发者和个人开发者提供快速开发应用的解决方案。您可以通过下面的渠道来支持我们的项目，

<div style="display:flex;" id="target">
<img src="https://github.com/CostCost/Resources/blob/master/github/ali.jpg?raw=true" width="25%" />
<img src="https://github.com/CostCost/Resources/blob/master/github/mm.png?raw=true" style="margin-left:10px;" width="25%"/>
</div>

## License

```
Copyright (c) 2019-2020 DaddyDev.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```





