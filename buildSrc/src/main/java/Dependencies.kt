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

    //UnitTest
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExt}" }
}