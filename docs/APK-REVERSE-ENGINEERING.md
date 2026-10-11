# Gboard Font Studio — APK reverse-engineering notes

Target examined: Google Gboard APK 18.4.1, ARM64, package `com.google.android.inputmethod.latin`.

## Confirmed observations

- The APK contains four DEX files.
- `com.google.android.libraries.inputmethod.widgets.SoftKeyView` extends `FrameLayout` and overrides `onDraw(Canvas)`.
- That draw method delegates to the obfuscated interface `Ltdj;.a(SoftKeyView)`. The interface is declared in `classes3.dex`; a concrete implementation, `Lpey;.a(SoftKeyView)`, is present in `classes2.dex`. That implementation delegates again through an `Lpxj;` field, so this is not yet a proven font-replacement hook.
- `com.google.android.libraries.inputmethod.widgets.text.AutoCenterScaleTextView` extends `TransformationTextView` and overrides `setTypeface(Typeface)`. Its implementation calls the superclass setter and invalidates the view.
- Compiled layout resources include `AutoCenterScaleTextView` within keyboard-related view hierarchies. This makes it a promising candidate for testing, but does **not** yet prove it renders the main Latin letter on every Gboard layout.
- The only TTF/OTF asset found in this APK was `res/dMC.ttf`; its presence alone does not establish that it controls Latin key labels.

## Engineering decision

Do not inject into `SoftKeyView.onDraw` based only on the current evidence. The next safe step is to fingerprint the exact `AutoCenterScaleTextView.setTypeface(Typeface)` method in the supplied APK, then validate which keyboard layouts instantiate it and whether the primary Latin glyphs pass through it. A patch that merely compiles is not proof of runtime behavior.

## Validation status

- APK structure and class/method signatures: inspected locally.
- Target fingerprint matched by Morphe against this APK: not yet tested.
- Patched APK installed and keyboard glyphs visually verified: not yet tested.
- User-supplied arbitrary font file loading / font picker: not implemented yet.

Morphe's documented approach supports injecting calls into matched bytecode methods and bundling runtime extension DEX code; the target-specific hook still needs to be proven before shipping a functional font patch.
