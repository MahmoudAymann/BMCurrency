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
    val getProperty = { k: String, v: String -> "\"${project.properties["$k.$v"]}\"" }
    val getFixerProperty = { v: String -> getProperty("Fixer", v) }

    defaultConfig {
        applicationId = "com.bm.currency"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "FixerApiKey", getFixerProperty("ApiKey"))
        buildConfigField("String", "FixerBaseUrl", getFixerProperty("BaseUrl"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    testOptions {
        unitTests.isReturnDefaultValues = true
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

    //Moshi
    implementation(Deps.moshi)
    implementation(Deps.moshiKotlin)
    ksp(Deps.moshiKotlinCodegen)
    implementation(Deps.moshiRetrofitConverter)
    implementation(Deps.kotlinCoroutines)
    implementation(Deps.kotlinCoroutinesTest)


    testImplementation(Deps.jUnit)
    testImplementation(Deps.mockitoCore)
    testImplementation(Deps.coreTesting)
    androidTestImplementation(Deps.jUnitExt)
    testImplementation("org.json:json:20210307")
}