<h1 align="center">Android-Utils: a collection of Android utils</h1>

<p align="center">
  <a href="http://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/hexpm/l/plug.svg" alt="License" />
  </a>
  <a href="https://bintray.com/beta/#/easymark/Android/utils-core?tab=overview">
    <img src="https://img.shields.io/maven-metadata/v/https/s01.oss.sonatype.org/service/local/repo_groups/public/content/com/github/Shouheng88/utils-core/maven-metadata.xml.svg" alt="Version" />
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
</P>

## 1. Introcution

This project is mainly used to collect utils classes useful in Android development, for example, log, crash collection, resources, attr, runtime permission, toast etc so that to accelerate your Android evelopment.

## 2. Use in your project

### 2.1 Add dependency

This project is published to jCenter, so you can easily use it in your project.

First, you need to add jcenter in your project:

```gradle
repositories { mavenCentral() }
```

then, add dependencies in your project:

```gradle
implementation "com.github.Shouheng88:utils-core:$latest-version"
```

If you want to use the kotlin extension based on utils classes, use the dependency below:

```gradle
implementation "com.github.Shouheng88:utils-ktx:$latest-version"
```

### 2.2 Initialize

You need initialize utils in your application. This is mainly used to get a global context to make utils methods more convenient, and it will cost too much time of launching your App.

```java
public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize your library
        UtilsApp.init(this);
    }
}
```

### 2.2 Fetures and functions

The utils classes was divided into multiple groups:

|No|Class|Group|Desc|
|:---:|:---:|:---:|:---:|
|1|[ActivityUtils](./utils/src/main/java/me/shouheng/utils/app/ActivityUtils.java)|App|Activity start, close, aniation, builder|
|2|[AppUtils](./utils/src/main/java/me/shouheng/utils/app/AppUtils.java)|App|App install, uninstall, get information
|3|[IntentUtils](./utils/src/main/java/me/shouheng/utils/app/IntentUtils.java)|App|Intent to launch App, market etc
|4|[ResUtils](./utils/src/main/java/me/shouheng/utils/app/ResUtils.java)|Resources|Image, Text, and other resources
|5|[EncodeUtils](./utils/src/main/java/me/shouheng/utils/data/EncodeUtils.java)|Encode|base64 and url encode
|6|[EncryptUtils](./utils/src/main/java/me/shouheng/utils/data/EncryptUtils.java)|Encrypt|md5 sha256 encrypt
|7|[RegexUtils](./utils/src/main/java/me/shouheng/utils/data/RegexUtils.java)|Data|Regex
|8|[StringUtils](./utils/src/main/java/me/shouheng/utils/data/StringUtils.java)|Data|String process
|9|[TimeUtils](./utils/src/main/java/me/shouheng/utils/data/TimeUtils.java)|Data|Date and time process
|10|[DeviceUtils](./utils/src/main/java/me/shouheng/utils/device/DeviceUtils.java)|Device|Device info, imei, model etc
|11|[NetworkUtils](./utils/src/main/java/me/shouheng/utils/device/NetworkUtils.java)|Device|Network info, type etc
|12|[ShellUtils](./utils/src/main/java/me/shouheng/utils/device/ShellUtils.java)|Device|Shell
|13|[PermissionUtils](./utils/src/main/java/me/shouheng/utils/permission/PermissionUtils.java)|Permission|Android runtime permission
|14|[CrashHelper](./utils/src/main/java/me/shouheng/utils/stability/CrashHelper.java)|Stability|crash
|15|[L](./utils/src/main/java/me/shouheng/utils/stability/L.java)|Stability|log output, format etc
|16|[FileUtils](./utils/src/main/java/me/shouheng/utils/store/FileUtils.java)|Storage|File, visit, move, delete etc
|17|[PathUtils](./utils/src/main/java/me/shouheng/utils/store/PathUtils.java)|Storage|Get directory paths
|18|[IOUtils](./utils/src/main/java/me/shouheng/utils/store/IOUtils.java)|Storage|IO|
|19|[SPUtils](./utils/src/main/java/me/shouheng/utils/store/SPUtils.java)|Storage|Sharedpreference|
|20|[ImageUtils](./utils/src/main/java/me/shouheng/utils/ui/ImageUtils.java)|ui|Image process, scale, roate etc|
|21|[ToastUtils](./utils/src/main/java/me/shouheng/utils/ui/ToastUtils.java)|ui|Toast|
|22|[ViewUtils](./utils/src/main/java/me/shouheng/utils/ui/ViewUtils.java)|ui|View info etc|

### 2.3 Use Utils-ktx

`utils-ktx` is a kotlin extension based on Android-Utils, which is used to simplify usages of utils classes. For example, if you want to get a drawable, tint it and then display it in ImageView. One line is enough:

```kotlin
iv.icon = drawableOf(R.drawable.ic_add_circle).tint(Color.WHITE)
```

If you want to request runtime permission in Activity, what you need to do is only writing one line below,

```kotlin
checkStoragePermission {  /* got permission */ }
```

As for avoding continous click, you only need one line, 

```kotlin
btnRateIntro.onDebouncedClick { /* do something */ }
```

All in all, by utils-ktx you can significantly lower the difficulty of development.

## 3、About

### 3.1 Change log

[Log](CHANGELOG.md)

## License

```
Copyright (c) 2019-2021 wsh.

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

