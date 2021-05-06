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

fun Context.openPlayStore(packageName: String) {
    runCatching {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
    }.recoverCatching {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}

fun Context.openWeb(url: String) {
    startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    )
}

fun Context.openPhone(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("${OPEN_PHONE_KEY}$phoneNumber")
    startActivity(intent)
}

fun Context.openEmail(email: String, title: String) {
    val emailIntent = Intent(
        Intent.ACTION_SENDTO, Uri.fromParts(OPEN_MAIL_KEY, email, null)
    )
    startActivity(Intent.createChooser(emailIntent, title))
}

const val OPEN_PHONE_KEY = "tel:"
const val OPEN_MAIL_KEY = "mailto"
