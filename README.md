# Gboard Font Studio

Morphe patch project exploring custom-font support for Google Gboard.

> **Development status:** experimental hook-probe stage. An opt-in diagnostic patch now targets a candidate typeface setter, but a user-selectable custom-font feature is not implemented or runtime-validated. Do not treat a successful build as proof that font customization works.

## Goal

Develop a Morphe `.mpp` patch that can help users customize Gboard's typeface while keeping the original app patchable through Morphe Desktop/Manager.

## Build

Requirements: Java 21 and the Gradle wrapper included in this repository.

```bash
./gradlew :patches:buildAndroid --no-daemon
```

The patch bundle is expected at `patches/build/libs/patches-*.mpp`. Apply it with [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop).

## GitHub Actions

The Build Patch workflow builds the bundle for pushes and pull requests and uploads it as a workflow artifact.

## Current work

- [x] Configure Morphe project metadata for Gboard Font Studio.
- [x] Add a CI workflow to build and upload the `.mpp` artifact.
- [x] Inspect the supplied Gboard 18.4.1 APK and record candidate font hooks in [the reverse-engineering notes](docs/APK-REVERSE-ENGINEERING.md).
- [x] Add an opt-in diagnostic probe that substitutes system monospace at the candidate setter.
- [x] Confirm the project and extension bundle compile in GitHub Actions.
- [ ] Confirm the fingerprint matches when Morphe applies the probe to the supplied APK.
- [ ] Verify visually whether primary Latin key labels use this setter.
- [ ] Implement user-selectable custom-font loading and test glyph fallback/layout/stability.

## Compatibility and safety

The probe is pinned to Gboard 18.4.1 because that is the APK currently inspected. It is disabled by default and should remain experimental until the fingerprint and visible keyboard behavior are tested. Keep a copy of the original APK and use Morphe's normal patching workflow.

## License

GPL-3.0; see [LICENSE](LICENSE).
