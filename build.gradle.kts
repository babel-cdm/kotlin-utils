import AndroidConfiguration.COMPILE_SDK_VERSION
import AndroidConfiguration.MIN_SDK_VERSION
import BuildType.Companion.DEBUG
import BuildType.Companion.RELEASE
import dependencies.Dependencies.COROUTINES
import dependencies.Dependencies.KOTLIN
import dependencies.Dependencies.KTX_CORE
import dependencies.Dependencies.LOGGING_INTERCEPTOR
import dependencies.Dependencies.Retrofit
import dependencies.Dependencies.TIMBER
import dependencies.TestDependencies.JUNIT
import dependencies.TestDependencies.MOCKITO
import dependencies.TestDependencies.ROBOLECTRIC

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.JACOCO)
    id(Plugins.JACOCO_REPORT)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdk = COMPILE_SDK_VERSION

    if (gradle.gradleVersion >= "8.0") {
        namespace = "es.babel.cdm.utils"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    defaultConfig {
        minSdk = MIN_SDK_VERSION
    }

    buildTypes {
        getByName(DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.isTestCoverageEnabled
        }
        getByName(RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.isTestCoverageEnabled
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        map { sourceSet -> sourceSet.java.srcDir("src/${sourceSet.name}/kotlin") }
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    lint {
        disable += "ObsoleteLintCustomCheck" +
        "UnusedAttribute" +
        "VectorPath" +
        "Overdraw" +
        "IconLocation" +
        "RtlEnabled" +
        "RtlSymmetry"
        warningsAsErrors = true
    }

    testCoverage.jacocoVersion = "0.8.7"
}

dependencies {
    implementation(KOTLIN)
    implementation(KTX_CORE)

    implementation(TIMBER)

    implementation(Retrofit.CORE)
    implementation(Retrofit.GSON)
    implementation(Retrofit.SCALARS)
    implementation(Retrofit.COROUTINES_ADAPTER)
    implementation(COROUTINES)

    implementation(LOGGING_INTERCEPTOR)

    testImplementation(JUNIT)
    testImplementation(ROBOLECTRIC)
    testImplementation(MOCKITO)
}
