class AppDependencies {

    /**
     * Root level build.gradle.kts.kts.kts file
     */
    object BuildPlugins {
        val gradle by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
        val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}" }
        val googleServices by lazy { "com.google.gms:google-services:${Versions.googleServices}" }

    }

    object Deps{

        object AndroidX {
            val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
            val recyclerView by lazy { "androidx.recyclerview:recyclerview:${Versions.recyclerView}" }
            val cardView by lazy { "androidx.cardview:cardview:${Versions.support}" }
        }

        object Networking {
            val retrofitRuntime by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
            val retrofitGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverts}" }
            val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
            val okhttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
        }

        object Kotlin {
            val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinPlugin}" }
            val kotlinCoreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
            val kotlinFragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
            val kotlinLifecycleKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}" }
        }

        object Dagger {
            val daggerRuntime by lazy { "com.google.dagger:dagger:${Versions.dagger}" }
            val daggerAndroid by lazy { "com.google.dagger:dagger-android:${Versions.dagger}" }
            val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Versions.dagger}" }
            val daggerProcessor by lazy { "com.google.dagger:dagger-android-processor:${Versions.dagger}" }
            val daggerAndroidSupport by lazy { "com.google.dagger:dagger-android-support:${Versions.dagger}" }
        }

        object Others {
            val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
            val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
            val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
            val prefsDataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.prefsDataStore}" }
            val glideRuntime by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
            val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
            val androidsvg by lazy { "com.caverock:androidsvg:${Versions.androidsvg}" }

        }

        object Firebase {
            val firebaseAuth by lazy { "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}" }
            val firebaseDataBase by lazy { "com.google.firebase:firebase-database-ktx:${Versions.firebaseDataBase}" }
        }

        object Testing {
            val junit by lazy { "junit:junit:${Versions.jUnit}" }
            val atslExtJunit by lazy { "androidx.test.ext:junit:${Versions.atslJunit}" }
            val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}" }
            val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockito}" }
            val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockitoAndroid}" }
            val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
            val espressoContrib by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espresso}" }
            val mockitoKotlin by lazy { "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}" }
            val truth by lazy { "com.google.truth:truth:${Versions.truth}" }
            val kotlinCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}" }
            val androidXcoreTesting by lazy { "androidx.arch.core:core-testing:${Versions.archCore}" }
            val androidRunner by lazy { "androidx.test:runner:${Versions.runner}" }

        }
    }
}