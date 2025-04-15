package dev.stupak.navigation.ext

import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun <T : Any> NavHostController.safeNavigation(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    cleanBackStack: Boolean = false,
) {
    try {
        if (cleanBackStack) {
            this.popBackStack()
        }
        this.navigate(route, navOptions, navigatorExtras)
    } catch (ex: Exception) {
        Log.e("Navigation", "Excetion", ex)
    }
}

fun NavHostController.safeNavigation(
    deeplink: String,
    cleanBackStack: Boolean = false,
) {
    try {
        if (cleanBackStack) {
            this.popBackStack()
        }
        this.navigate(deeplink)
    } catch (ex: Exception) {
        Log.e("Navigation", "Excetion", ex)
    }
}
