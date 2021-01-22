package es.babel.cdm.utils.constants

object Root {
    const val STARTING_COUNT = 0

    object Frida {
        const val CWD_FORMAT = "/proc/%d/maps"
        val places = arrayOf("/data/local/tmp/")
        val binaryName = "re.frida.server"
        val installed = arrayOf("frida")
    }

    object Su {
        const val BINARY_NAME = "su"
        val BINARY_PLACES = arrayOf(
            "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
            "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
        )
    }

    object Magisk {
        val blackList = arrayOf(
            "/sbin/.magisk/", "/sbin/.core/mirror", "/sbin/.core/img", "/sbin/.core/db-0/magisk.db"
        )
        const val LABEL = "magisk"
        const val CWD_FORMAT = "/proc/%d/mounts"
    }
}
