plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinter)
}

android {
    compileSdkVersion(Sdk.compile)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        map { sourceSet -> sourceSet.java.srcDir("src/${sourceSet.name}/kotlin") }
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)

    implementation(Libraries.Retrofit.core)
    implementation(Libraries.Retrofit.gson)
    implementation(Libraries.Retrofit.scalars)
    implementation(Libraries.Retrofit.courutinesAdapter)

    implementation(Libraries.OkHttpClient.loggingInterceptor)

    implementation(Libraries.timber)
}