package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.String.BAR
import java.io.File
import java.util.UUID

fun ByteArray.toFile(directory: String, fileName: String? = null): File {
    val file = File(directory + BAR + (fileName ?: UUID.randomUUID()))
    file.writeBytes(this)

    return file
}
