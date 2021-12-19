package me.shouheng.utils.ktx

import me.shouheng.utils.store.FileUtils
import java.io.File

/**
 * The kotlin extensions for [File].
 *
 * @Author wangshouheng
 * @Time 2021/12/19
 */
fun File?.md5(): String = FileUtils.getFileMD5ToString(this)

fun File?.isFile(): Boolean = FileUtils.isFile(this)

fun File?.isDir(): Boolean = FileUtils.isDir(this)

fun File?.rename(name: String): Boolean = FileUtils.rename(this, name)

fun File?.copyTo(dest: File): Boolean = FileUtils.copyFile(this, dest)

fun File?.moveTo(dest: File): Boolean = FileUtils.moveFile(this, dest)

fun File?.delete(): Boolean = FileUtils.delete(this)
