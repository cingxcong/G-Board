package app.template.patches.example

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.template.patches.shared.Constants.COMPATIBILITY_GBOARD_18_4_1

/**
 * Diagnostic only: temporarily substitutes a system monospace typeface.
 * This is intended to prove whether AutoCenterScaleTextView controls the
 * keyboard's primary letter labels. It is not the final custom-font feature.
 */
@Suppress("unused")
val fontHookProbePatch = bytecodePatch(
    name = "Gboard Font Hook Probe (Experimental)",
    description = "Diagnostic test for the candidate Gboard key-label typeface hook. Uses system monospace; not a custom-font selector.",
    default = false
) {
    compatibleWith(COMPATIBILITY_GBOARD_18_4_1)
    extendWith("extensions/extension.mpe")

    execute {
        AutoCenterScaleTypefaceFingerprint.method.addInstructions(
            0,
            """
                invoke-static {p1}, Lapp/template/extension/extension/GboardFontHookProbe;->replaceTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;
                move-result-object p1
            """.trimIndent()
        )
    }
}
