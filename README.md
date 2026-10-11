# Gboard Font Studio

Morphe patch project exploring custom-font support for Google Gboard.

> **Development status:** experimental hook-probe stage. An opt-in diagnostic patch now targets a candidate typeface setter, but a user-selectable custom-font feature is not implemented or runtime-validated. Do not treat a successful build as proof that font customization works.

## Goal

Develop a Morphe `.mpp` patch that can help users customize Gboard's typeface while keeping the original app patchable through Morphe Desktop/Manager.

## Install in Morphe (important)

For the current experimental build, download the **`.mpp` file itself** from the latest successful [Build Patch workflow run](https://github.com/cingxcong/G-Board/actions/workflows/build-patch.yml). Expand the downloaded workflow-artifact ZIP first, then select the `.mpp` inside it in Morphe → Sources → **+** → Local. Do not select the outer artifact ZIP, and do not reuse an older `.mpp` from an earlier build.

The CI run also generates `patches-list.json` as a discovery report. That JSON is not the local patch bundle; use the `.mpp` file. The feature is still an experimental diagnostic probe, so even when Morphe lists it, the typeface hook has not yet been confirmed to change the visible key legends.

For a remote source, Morphe needs a published release/metadata. This feature branch is not a release branch, so adding the repository URL alone may show no patches until a release is published. Do not manually edit the generated `patches-list.json` or `patches-bundle.json`.

## Build

Requirements: Java 21 and the Gradle wrapper included in this repository.

```bash
./gradlew :patches:buildAndroid --no-daemon
```

The patch bundle is expected at `patches/build/libs/patches-*.mpp`. Apply it with [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop).

## GitHub Actions

The Build Patch workflow builds the bundle for pushes and pull requests and uploads it as a workflow artifact.

## Published patches

<!-- PATCHES_START EXPANDED -->
> **[v1.0.0-beta.2](https://github.com/cingxcong/G-Board/releases/tag/v1.0.0-beta.2)**&nbsp;&nbsp;•&nbsp;&nbsp;`feat/gboard-font-studio-foundation`&nbsp;&nbsp;•&nbsp;&nbsp;1 patches total
<details open>
<summary>📦 Gboard&nbsp;&nbsp;•&nbsp;&nbsp;1 patch</summary>
<br>

**🎯 Supported versions:**

| 18.4.1 | 18.5.1.993608717-beta-arm64-v8a |
| :---: | :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Gboard Font Hook Probe (Experimental)](#gboard-font-hook-probe-experimental) | Diagnostic test for the candidate Gboard key-label typeface hook. Uses system monospace; not a custom-font selector. |  |

</details>

<!-- PATCHES_END -->

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
