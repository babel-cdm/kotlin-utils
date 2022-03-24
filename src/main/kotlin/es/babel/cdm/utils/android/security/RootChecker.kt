package es.babel.cdm.utils.android.security

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import es.babel.cdm.utils.constants.Root
import es.babel.cdm.utils.constants.Root.STARTING_COUNT
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun checkIsRooted(context: Context) =
    withContext(Dispatchers.Default) {
        findSuBinary() || isMagiskInstalled(context) || isFridaInstalled() ||
            isMagiskPathInstalled() || isFridaPathInstalled()
    }

private fun findBinary(places: Array<String>, binaryName: String): Boolean {
    places.forEach {
        if (File(it + binaryName).exists()) {
            return true
        }
    }
    return false
}

private fun findSuBinary(): Boolean = findBinary(Root.Su.BINARY_PLACES, Root.Su.BINARY_NAME)

@SuppressLint("QueryPermissionsNeeded")
private fun isMagiskInstalled(context: Context): Boolean {
    context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA).forEach {
        if (it.packageName.lowercase(Locale.getDefault()).contains(Root.Magisk.LABEL) ||
            it.sourceDir.lowercase(Locale.getDefault()).contains(Root.Magisk.LABEL) ||
            context.packageManager.getApplicationLabel(it)
                .toString().lowercase(Locale.getDefault())
                .contains(Root.Magisk.LABEL)
        ) return true
    }
    return false
}

private fun isMagiskPathInstalled() =
    isAppBlackListedInstalled(
        String.format(Root.Magisk.CWD_FORMAT, Process.myPid()), Root.Magisk.blackList
    )

private fun isFridaInstalled() =
    findBinary(Root.Frida.places, Root.Frida.binaryName)

private fun isFridaPathInstalled() =
    isAppBlackListedInstalled(
        String.format(Root.Frida.CWD_FORMAT, Process.myPid()), Root.Frida.installed
    )

private fun isAppBlackListedInstalled(
    cwd: String,
    blackListedMountPaths: Array<String>
): Boolean {
    var fis: FileInputStream? = null
    var reader: BufferedReader? = null
    var isInstalled = false
    runCatching {
        fis = FileInputStream(File(cwd))
        reader = BufferedReader(InputStreamReader(fis))
        var str: String
        var count = STARTING_COUNT
        reader?.let { reader ->
            while (reader.readLine().also { str = it } != null) {
                for (path in blackListedMountPaths) {
                    if (str.contains(path)) {
                        Timber.d("Blacklisted - Path found $path")
                        count++
                    }
                }
            }
        }
        Timber.d("Blacklisted - Count of paths $count")
        isInstalled = count > STARTING_COUNT
    }.onFailure {
        Timber.e(it, "Blacklisted - Error checking Black listed paths")
        isInstalled = false
    }.onSuccess {
        fis?.close()
        reader?.close()
    }
    return isInstalled
}
