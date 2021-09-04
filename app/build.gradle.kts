plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")

}

android {
    compileSdkVersion(ConfigData.compileSdk)
    buildToolsVersion(ConfigData.buildTools)
    defaultConfig {
        applicationId = "com.rebtel.countries"
        minSdkVersion(ConfigData.minSdk)
        targetSdkVersion(ConfigData.targetSdk)
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(AppDependencies.Deps.Kotlin.kotlin)
    implementation(AppDependencies.Deps.Kotlin.kotlinCoreKtx)
    implementation(AppDependencies.Deps.Kotlin.kotlinLifecycleKtx)

    implementation(AppDependencies.Deps.AndroidX.appCompat)
    implementation(AppDependencies.Deps.AndroidX.recyclerView)
    implementation(AppDependencies.Deps.AndroidX.cardView)
    implementation(AppDependencies.Deps.Others.materialDesign)

    implementation(AppDependencies.Deps.Others.timber)
    implementation(AppDependencies.Deps.Others.constraintLayout)
    implementation(AppDependencies.Deps.Kotlin.kotlinFragmentKtx)

    //Net-working
    implementation(AppDependencies.Deps.Networking.retrofitRuntime)
    implementation(AppDependencies.Deps.Networking.retrofitGson)
    implementation(AppDependencies.Deps.Networking.okhttp)
    implementation(AppDependencies.Deps.Networking.okhttpLoggingInterceptor)

    //Dagger
    implementation(AppDependencies.Deps.Dagger.daggerRuntime)
    implementation(AppDependencies.Deps.Dagger.daggerAndroid)
    implementation(AppDependencies.Deps.Dagger.daggerAndroidSupport)
    kapt(AppDependencies.Deps.Dagger.daggerCompiler)
    kapt(AppDependencies.Deps.Dagger.daggerProcessor)

    implementation(AppDependencies.Deps.Others.glideRuntime)
    kapt(AppDependencies.Deps.Others.glideCompiler)
    implementation(AppDependencies.Deps.Others.androidsvg)


    //Testing
    testImplementation(AppDependencies.Deps.Testing.junit)
    testImplementation(AppDependencies.Deps.Testing.truth)
    testImplementation(AppDependencies.Deps.Testing.mockWebServer)
    testImplementation(AppDependencies.Deps.Testing.mockitoCore)

}