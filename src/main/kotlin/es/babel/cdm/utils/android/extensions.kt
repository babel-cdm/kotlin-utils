package es.babel.cdm.utils.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

private const val PACKAGE_SCHEME = "package"

fun Context.openAppSettings(packageName: String) {
    val uri = Uri.fromParts(PACKAGE_SCHEME, packageName, null)
    val intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = uri
    }

    startActivity(intent)
}
