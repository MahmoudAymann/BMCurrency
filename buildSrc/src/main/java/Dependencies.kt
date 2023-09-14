/**
 * Created by Mahmoud Ayman on 10/09/2023.
 * Email: mahmoud_aymann@outlook.com.
 */

object Deps {
    //Core
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKts}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }

    //Navigation
    val navFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }

    //Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }


    // Moshi
    val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshi}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" }
    val moshiRetrofitConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiRetrofitConverter}" }

    // Retrofit
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val okhttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}" }

    //UnitTest
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExt}" }
}