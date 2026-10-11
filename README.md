# Gboard Font Studio

Morphe patch project exploring custom-font support for Google Gboard.

> **Development status:** foundation setup. The actual Gboard font-replacement patch is not yet implemented or validated against a target APK. Do not treat a successful template build as proof that font customization works.

## Goal

Develop a Morphe `.mpp` patch that can help users customize Gboard's typeface while keeping the original app patchable through Morphe Desktop/Manager.

## Build

Requirements: Java 21 and the Gradle wrapper included in this repository.

```bash
./gradlew :patches:buildAndroid --no-daemon
```

The patch bundle is expected at `patches/build/libs/patches-*.mpp`. Apply it with [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop).

## GitHub Actions

The Release workflow verifies compilation and handles semantic releases. The Build Patch workflow builds the bundle for pushes and pull requests and uploads it as a workflow artifact.

## Current work

- [x] Configure Morphe project metadata for Gboard Font Studio.
- [x] Add a CI workflow to build and upload the `.mpp` artifact.
- [ ] Replace the template example patch with a Gboard-specific font patch.
- [ ] Fingerprint and test a supported Gboard APK version.
- [ ] Document how to provide/select a font and apply the patch safely.

## Compatibility and safety

Gboard implementation details may vary by version and device architecture. A patch must be tested against an explicitly identified APK before it is marked compatible. Keep a copy of the original APK and use Morphe's normal patching workflow.

## License

GPL-3.0; see [LICENSE](LICENSE).
