package app.template.extension.extension;

import android.graphics.Typeface;

/**
 * Temporary diagnostic helper. The final font feature will replace this
 * system-font substitution with a validated user-selected font source.
 */
@SuppressWarnings("unused")
public final class GboardFontHookProbe {
    private GboardFontHookProbe() {}

    public static Typeface replaceTypeface(Typeface original) {
        return Typeface.create("monospace", Typeface.NORMAL);
    }
}
