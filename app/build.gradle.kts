plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.commonlauncher.nativeplugin"
    compileSdk = 34

    signingConfigs {
        create("releaseBuild") {
            storeFile = rootProject.file("common_plugin.jks")
            storePassword = "common_plugin"
            keyAlias = "common_plugin"
            keyPassword = "common_plugin"
        }
    }

    defaultConfig {
        applicationId = "com.commonlauncher.nativeplugin"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("src/main/jniLibs")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            // Application name
            resValue("string", "app_name", "ZTSD")

            // Package name suffix
            applicationIdSuffix = ".zstd_dh_15712"

            // Plugin description shown in the launcher
            manifestPlaceholders["des"] = "zstd-jni 1.5.7-12-dhcompat"

            // JVM native library path
            manifestPlaceholders["environment"] = mutableMapOf<String, String>().apply {
                put(
                    "ZstdNativePath",
                    "{nativeLibraryDir}libzstd-jni_dh-1.5.7-12.so"
                )
            }.run {
                buildList {
                    this@run.forEach { (key, value) ->
                        add("-D$key=$value")
                    }
                }.joinToString(" ")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
}
