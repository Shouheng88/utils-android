# Android-Utils: 常用的 Android 工具库合集

![SLicense](https://img.shields.io/hexpm/l/plug.svg)
![Version](https://img.shields.io/maven-metadata/v/https/dl.bintray.com/easymark/Android/me/shouheng/utils/utils-core/maven-metadata.xml.svg)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/58b18f9bf47543cbbaf4ca67bcadfc7b)](https://www.codacy.com/manual/Shouheng88/Android-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Shouheng88/Android-utils&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Shouheng88/Android-utils.svg?branch=master)](https://travis-ci.org/Shouheng88/Android-utils)

## 1、关于项目

该项目作为笔者整个 MVVM 框架的一部分，主要用来收集日常开发中使用比较频繁的工具类，比如日志、Crash 收集、控件和各种资源等。你可以参考下文中的表格来了解这个项目中目前收集的工具类库。更多的功能正在开发和收集中……

除了笔者自己开发一些类库之外，我们也参考了很多现有的比较好的库中的类库，并对它们进行了精心的筛选，最终整合到该项目当中。

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
    implementation 'me.shouheng.utils:utils-core:latest-version'
}
```

然后你需要在应用启动的地方，也就是自定义的 Application 中对工具类库进行初始化：

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

然后，对一些类库你可以根据自己的需求进行个性化的定制，具体直接参考各个类库的源码。

### 2.2 该类库中目前提供的功能

按照关注的主题，我们将项目中的类按照主题分成了几个大类。目前项目中的类的结构如下：

|编号|类名|群组|说明|
|:-:|:-:|:-:|:-:|
|1|[ActivityUtils](./utils/src/main/java/me/shouheng/utils/app/ActivityUtils.java)|app|Activity 启动、关闭、动画、启动参数构建|
|2|[AppUtils](./utils/src/main/java/me/shouheng/utils/app/AppUtils.java)|app|App 安装与卸载，获取应用信息等
|3|[IntentUtils](./utils/src/main/java/me/shouheng/utils/app/IntentUtils.java)|app|各种比较通用的意图，比如分享文字、打开应用商店等
|4|[ResUtils](./utils/src/main/java/me/shouheng/utils/app/ResUtils.java)|app|图片、字符串和其他资源获取
|5|[EncodeUtils](./utils/src/main/java/me/shouheng/utils/data/EncodeUtils.java)|data|base64 和 url 编码等
|6|[EncryptUtils](./utils/src/main/java/me/shouheng/utils/data/EncryptUtils.java)|data|md5 sha256 加密方式
|7|[RegexUtils](./utils/src/main/java/me/shouheng/utils/data/RegexUtils.java)|data|正则表达式
|8|[StringUtils](./utils/src/main/java/me/shouheng/utils/data/StringUtils.java)|data|字符串处理
|9|[TimeUtils](./utils/src/main/java/me/shouheng/utils/data/TimeUtils.java)|data|时间处理，时间和日期格式化等
|10|[DeviceUtils](./utils/src/main/java/me/shouheng/utils/device/DeviceUtils.java)|device|设备信息，比如 imei 手机型号 生产商等
|11|[NetworkUtils](./utils/src/main/java/me/shouheng/utils/device/NetworkUtils.java)|device|网络信息，网络类型等
|12|[ShellUtils](./utils/src/main/java/me/shouheng/utils/device/ShellUtils.java)|device|命令行执行
|13|[PermissionUtils](./utils/src/main/java/me/shouheng/permission/app/PermissionUtils.java)|permission|Android 6.0 运行时权限权限请求
|14|[CrashHelper](./utils/src/main/java/me/shouheng/utils/store/CrashHelper.java)|store|崩溃日志
|15|[LogUtils](./utils/src/main/java/me/shouheng/utils/store/LogUtils.java)|store|日志输出，日志格式化，日志输出到文档等
|16|[FileUtils](./utils/src/main/java/me/shouheng/utils/store/FileUtils.java)|store|文件相关，获取文件信息，文件移动，删除和遍历等
|17|[PathUtils](./utils/src/main/java/me/shouheng/utils/store/PathUtils.java)|store|文件路径，获取手机中各个文件夹的路径等
|18|[IOUtils](./utils/src/main/java/me/shouheng/utils/store/IOUtils.java)|store|磁盘读写|
|19|[SPUtils](./utils/src/main/java/me/shouheng/utils/store/SPUtils.java)|store|Sharedpreference 读写相关|
|20|[ImageUtils](./utils/src/main/java/me/shouheng/utils/ui/ImageUtils.java)|ui|图片处理，缩放，旋转，变形，加水印等|
|21|[ToastUtils](./utils/src/main/java/me/shouheng/utils/ui/ToastUtils.java)|ui|土司封装|
|22|[ViewUtils](./utils/src/main/java/me/shouheng/utils/ui/ViewUtils.java)|ui|获取控件信息以及软键盘操作等|

### 2.3 运行时权限的使用说明

像下面的代码显示的那样，你只需要让自己的 Acitivity 实现 PermissionResultResolver 接口，定义一个 OnGetPermissionCallback 实例，然后在 `onRequestPermissionsResult` 方法中调用 `PermissionResultHandler.handlePermissionsResult(...)` 就可以了。当你需要请求某个运行时权限的时候，使用 `PermisstionUtils` 的静态方法，并传入一个回调接口来执行当获取到权限的时候的逻辑即可。一般的，我们建议你在自己的顶层的 Activity 中进行上述操作，这样你就可以在所有继承了顶层 Activity 的子类中进行权限请求了。

```java
public class TestPermissionActivity extends AppCompatActivity implements PermissionResultResolver {

    private OnGetPermissionCallback onGetPermissionCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);

        findViewById(R.id.btn_storage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.checkStoragePermission(TestPermissionActivity.this,
                        new OnGetPermissionCallback() {
                            @Override
                            public void onGetPermission() {
                                Toast.makeText(TestPermissionActivity.this,
                                        R.string.permission_get_storage_permission,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // call permission result handler
        PermissionResultHandler.handlePermissionsResult(this, requestCode, permissions,
                grantResults, new PermissionResultCallbackImpl(this, onGetPermissionCallback));
    }

    @Override
    public void setOnGetPermissionCallback(OnGetPermissionCallback onGetPermissionCallback) {
        this.onGetPermissionCallback = onGetPermissionCallback;
    }
}
```

当然，上述操作的作用原理并不复杂。无法就是把权限请求结果的逻辑放在了 `PermissionResultHandler.handlePermissionsResult(...)` 当中执行。我们会根据用户的选择和当前请求的阶段来给用户进行提示。如果你希望对这个处理的过程进行个性化处理，你完全可以在 `PermissionResultHandler.handlePermissionsResult(...)` 方法中，通过传入一个自定义的 `PermissionResultCallbackImpl` 来进行处理。

## 3、更新日志

- 版本 1.3.6
    - SPUtils getInstance 方法重命名为 get，getInstance 太长
    - LogUtils 类重命名为 L，LogUtils 太长
    - 应用前后台切换的监听
    - 动画工具类修改名称
    - 权限工具类增加自定义权限名称的接口
- 版本 1.3.5
    - 解析 Theme 并读取属性值相关的工具类
- 版本 1.2.1：
    - ActivityUtils 增加了动画支持
    - StringUtils 增加了字符串拼接的方法
- 版本 1.1.0：
    - 增加了基本的类库

## 4、关于作者

你可以通过访问下面的链接来获取作者的信息：

1. Github: https://github.com/Shouheng88
2. 掘金：https://juejin.im/user/585555e11b69e6006c907a2a
3. CSDN：https://blog.csdn.net/github_35186068

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





