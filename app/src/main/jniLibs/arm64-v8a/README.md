# Native libraries: arm64-v8a

Place the DistantHorizonsZSTD 1.5.7-12 Android libraries in this directory:

- `libzstd-jni_dh-1.5.7-12.so` — loaded through the `ZstdNativePath` JVM property configured in `app/build.gradle.kts`.
- `libsqlitejdbc.so` — SQLite JNI library shipped by the DistantHorizonsZSTD project.

Upstream source (arm64-v8a):

- https://github.com/RaydanOMGr/DistantHorizonsZSTD/tree/main/src/main/resources/natives/arm64-v8a

Expected files must retain these exact filenames. Android Gradle packages native libraries from `src/main/jniLibs/<abi>/` into the APK's native library directory.

This plugin currently declares the arm64-v8a directory only. Add the matching ABI-specific binaries and directories before publishing builds for other Android ABIs.
