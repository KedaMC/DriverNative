plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.commonlauncher.nativeplugin"
    compileSdk = 34

    signingConfigs {
        create("releaseBuild") {
            storeFile = file("common_plugin.jks")
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
            // Native libs (.so) live under app/src/main/jniLibs/<abi>/
            jniLibs.srcDirs("app/src/main/jniLibs")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("releaseBuild")
        }
        configureEach {
            // Application name
            resValue("string", "app_name", "ZTSD")

            // Package name suffix
            applicationIdSuffix = ".zstd_dh_15712"

            // Plugin package display name within the launcher
            manifestPlaceholders["des"] = "zstd-jni 1.5.7-12-dhcompat"

            // JVM environment parameter configuration
            manifestPlaceholders["environment"] = "-DZstdNativePath={nativeLibraryDir}libzstd-jni_dh-1.5.7-12.so"
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
