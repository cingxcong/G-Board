# Gboard Font Studio — APK reverse-engineering notes

Target examined: Google Gboard APK 18.4.1, ARM64, package `com.google.android.inputmethod.latin`.

## Confirmed observations

- The APK contains four DEX files.
- `com.google.android.libraries.inputmethod.widgets.SoftKeyView` extends `FrameLayout` and overrides `onDraw(Canvas)`.
- That draw method delegates to the obfuscated interface `Ltdj;.a(SoftKeyView)`. Its concrete implementation `Lpey;.a(SoftKeyView)` is in `classes2.dex`; it reads an obfuscated field and checks the view's visibility. This path is not yet established as the right font-replacement hook.
- `com.google.android.libraries.inputmethod.widgets.text.AutoCenterScaleTextView` overrides `setTypeface(Typeface)`. The method calls `TransformationTextView.setTypeface(Typeface)` and invalidates the view afterward.
- Compiled layout resources include `AutoCenterScaleTextView` within keyboard-related view hierarchies. This makes it a promising candidate, but does **not** prove it renders the main Latin key labels across layouts.
- The only TTF/OTF asset found in this APK was `res/dMC.ttf`; its presence alone does not establish that it controls Latin key labels.

## Current experiment

The feature branch now contains an **opt-in diagnostic patch** named `Gboard Font Hook Probe (Experimental)`. It fingerprints the exact `AutoCenterScaleTextView.setTypeface(Typeface)` signature and substitutes Android's system monospace typeface. The purpose is to determine whether this view's typeface setter visibly affects primary key labels. It is not the final custom-font feature and is disabled by default.

## Validation status

- APK structure and class/method signatures: inspected locally.
- Morphe project / extension bundle compilation: **passed** in GitHub Actions.
- Fingerprint successfully matched by Morphe against this APK: not yet confirmed.
- Patched APK installed and keyboard glyphs visually verified: not yet tested.
- User-supplied arbitrary font loading / font picker: not implemented yet.

## Next steps

1. Apply the diagnostic patch to the exact supplied Gboard 18.4.1 APK with Morphe.
2. Confirm whether the fingerprint resolves and whether primary Latin key labels visibly become monospace.
3. If the view affects only secondary labels or other UI, trace the actual Latin key rendering path instead of shipping this hook.
4. Once the hook is proven, implement safe custom-font loading/selection and validate layout, fallback glyphs, and stability.

Morphe supports injecting bytecode into fingerprinted methods and bundling runtime extension DEX code. Successful compilation proves the patch bundle builds; it does not prove target matching or runtime behavior.
