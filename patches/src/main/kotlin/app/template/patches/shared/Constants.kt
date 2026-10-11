package app.template.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_GBOARD_18_4_1 = Compatibility(
        name = "Gboard",
        packageName = "com.google.android.inputmethod.latin",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xFF4285F4.toInt(),
        targets = listOf(
            // The only target currently investigated locally. Do not broaden until tested.
            AppTarget(version = "18.4.1")
        )
    )
}
