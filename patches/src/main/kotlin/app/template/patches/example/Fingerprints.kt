package app.template.patches.example

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * Exact signature observed in the supplied Gboard 18.4.1 ARM64 APK.
 * Runtime matching still needs to be confirmed by applying the patch to that APK.
 */
object AutoCenterScaleTypefaceFingerprint : Fingerprint(
    definingClass = "Lcom/google/android/libraries/inputmethod/widgets/text/AutoCenterScaleTextView;",
    name = "setTypeface",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("Landroid/graphics/Typeface;")
)
