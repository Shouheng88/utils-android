package me.shouheng.utils.ktx

private const val KILOBYTE = 1_024L
private const val MEGABYTE = 1_048_576L
private const val GIGABYTE = 1_073_741_824L

/** Converts byte length into a human-readable unit string, like 5GB. */
fun Long.friendlySize(): String {
    return when {
        this >= GIGABYTE -> "${this / GIGABYTE}GB"
        this >= MEGABYTE -> "${this / MEGABYTE}MB"
        this >= KILOBYTE -> "${this / KILOBYTE}KB"
        else -> "${this}B"
    }
}
