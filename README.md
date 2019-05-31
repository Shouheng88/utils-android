# Android-Utils: 常用的 Android 工具库合集

## 1、关于项目

该项目主要用来收集日常开发中使用比较频繁的工具类，比如日志、Crash 收集、控件等。你可以参考下文中的表格来了解这个项目中目前收集的工具类库。

该项目开发过程中除了自己开发一些类库之外，也会借鉴一些现有的比较好的库中的类，特别是 BlankJ 的 [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode).

## 2、使用

### 2.1 引用类库到你的项目

该项目已经被提交到了 jCenter，所以你可以很方便地在你的项目中使用它。

首先，你需要引用 jCenter 到你的项目中：

```gradle
repositories {
    jcenter()
}
```

然后，在你的 dependencies 中添加该类库的依赖：

```gradle
dependencies {
    implementation 'me.shouheng.utils:utils-core:1.1.0'
}
```

### 2.2 该类库中目前提供的功能

按照关注的主题，我们将项目中的类分成了几个大类。目前项目中的类的结构如下：

- app: 应用开发常用的类

    - ActivityHelper: Activity 相关
    - AppUtils: App 安装与卸载，获取应用信息等
    - IntentUtils: Intent 相关
    - ResUtils: 资源 Resources 相关

- data: 数据处理

    - EncodeUtils: 编码相关
    - EncryptUtils: 加密相关
    - RegexUtils: 正则表达式相关
    - StringUtils: 字符串相关
    - TimeUtils: 时间处理

- device: 设备信息

    - DeviceUtils: 设备相关
    - NetworkUtils: 网络相关
    - ShellUtils: Shell 命令相关

- permission: 权限

    - PermissionUtils: 权限相关

- stability: 稳定性

    - CrashHelper: Crash 拦截和日志收集
    - LogUtils: 日志打印相关

- store: 数据存储

    - FileUtils: 获取文件信息、文件增删等操作
    - PathUtils: 获取 Android 文件系统的各个目录
    - IOUtils: IO 读写相关
    - SPUtils: SharedPreferences 读写相关

- ui: ui 相关

    - ImageUtils: 基本的图片处理相关的逻辑
    - ToastUtils: Toast 相关的工具类
    - ViewUtils: View、尺寸信息、软键盘等

## 3、更新日志

- 版本 1.1.0

    - 增加了基本的类库

## 4、关于作者

你可以通过访问下面的链接来获取作者的信息：

1. Twitter: https://twitter.com/shouheng_wang
2. 微博：https://weibo.com/5401152113/profile?rightmod=1&wvr=6&mod=personinfo
3. Github: https://github.com/Shouheng88
4. 掘金：https://juejin.im/user/585555e11b69e6006c907a2a

## License

```
Copyright (c) 2019 WngShhng.

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





