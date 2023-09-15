plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.bm.currency"
    compileSdk = 34
    val getProperty = { k: String -> "\"${project.properties[k]}\"" }

    defaultConfig {
        applicationId = "com.bm.currency"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "FixerApiKey", getProperty("FixerApiKey"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)

    //Navigation
    implementation(Deps.navFragmentKtx)
    implementation(Deps.navUiKtx)

    //Hilt
    implementation(Deps.hiltAndroid)
    ksp(Deps.hiltCompiler)

    //Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.okhttpLoggingInterceptor)
//    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Moshi
    implementation(Deps.moshi)
    implementation(Deps.moshiKotlin)
    ksp(Deps.moshiKotlinCodegen)
    implementation(Deps.moshiRetrofitConverter)


    testImplementation(Deps.jUnit)
    androidTestImplementation(Deps.jUnitExt)
}