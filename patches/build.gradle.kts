group = "app.cingxcong.gboardfontstudio"

patches {
    about {
        name = "Gboard Font Studio"
        description = "Morphe patches for experimenting with font customization in Google Gboard."
        source = "https://github.com/cingxcong/G-Board"
        author = "cingxcong"
        contact = "https://github.com/cingxcong/G-Board/issues"
        website = "https://github.com/cingxcong/G-Board"
        license = "GPLv3"
    }
}

// Separate configuration so gson is available at runtime for the
// generatePatchesList task but never bundled into the APK.
val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    // Used by gradle-semantic-release-plugin.
    publish {
        dependsOn("generatePatchesList")
    }
}
