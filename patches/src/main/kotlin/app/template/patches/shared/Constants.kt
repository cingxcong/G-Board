package app.template.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_GBOARD_18_4_1 = Compatibility(
        name = "Gboard",
        packageName = "com.google.android.inputmethod.latin",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x4285F4,
        targets = listOf(
            // Original investigation target.
            AppTarget(version = "18.4.1"),
            // User-supplied APK: Gboard 18.5.1.993608717-beta (arm64-v8a).
            // The candidate class/method strings are present in this APK, but an
            // actual Morphe apply-and-run test is still required before calling
            // the hook verified.
            AppTarget(version = "18.5.1.993608717-beta-arm64-v8a")
        )
    )
}
