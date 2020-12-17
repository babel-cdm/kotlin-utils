import java.io.ByteArrayOutputStream
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec

class DESDecryptor {
    companion object {
        const val TAM_CLAVE_DES = 8
        private const val TAM_CLAVE_3DES = 16
        private const val BASE_HEXA = 16
        const val KEY = "1FB517AAA9AAE3ADD1A778ED39303B23"
    }

    fun decrypt(encryptString: String): String {
        return try {
            val cipher = Cipher.getInstance("DESede")
            cipher.init(Cipher.DECRYPT_MODE, genClaveDES(dameClave3DES(KEY.toByteArray())))
            String(cipher.doFinal(toByteArray(encryptString)))
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Encriptar.
     *
     * @param cadena
     * the cadena
     * @return the string
     * @.doc

    fun encriptar(cadena: String): String {
    var encriptado = ""
    try {
    val claveB = CTE_CIFRADO.toByteArray()
    val cipher = Cipher.getInstance("DESede")
    cipher.init(
    Cipher.ENCRYPT_MODE,
    genClaveDES(dameClave3DES(toByteArray(byte2Hex(claveB))))
    )
    val result = cipher.doFinal(cadena.toByteArray())
    encriptado = byte2Hex(result)
    } catch (e: Exception) {
    println(e.message)
    }
    return encriptado
    }
     */

    /**
     * To byte array.
     *
     * @param cadTrans
     * the cad trans
     * @return the byte[]
     * @.doc
     */
    private fun toByteArray(cadTrans: String): ByteArray {
        var cadena = ""
        var cadenaAux: String? = null
        var longitud = 0
        var posicion = 0
        cadena = cadTrans
        if (cadena.length % 2 != 0) {
            cadena = "0$cadena"
        }
        longitud = cadena.length / 2
        val salida = ByteArrayOutputStream()
        for (i in 0 until longitud) {
            cadenaAux = cadena.substring(posicion, posicion + 2)
            posicion += 2
            salida.write(cadenaAux.toInt(BASE_HEXA))
        }
        return salida.toByteArray()
    }

    /**
     * Byte2 hex.
     *
     * @param byteS
     * the byte s
     * @return the string
     * @.doc

    fun byte2Hex(byteS: ByteArray): String {
    val byteSB = StringBuilder(byteS.size)
    var bitS = ""
    for (i in byteS.indices) {
    val b = byteS[i]
    bitS = Integer.toHexString(b and 0xff)
    if (bitS.length < 2) {
    bitS = "0$bitS"
    }
    byteSB.append(bitS)
    }
    return byteSB.toString().toUpperCase()
    }
     */

    /**
     * MÈtodo que completa la clave recibida si es de 16 bytes a 24 bytes para
     * que el proveedor pueda generar correctamente el objeto SecretKey.
     *
     * @param claveMAC
     * clave recibida. SÛlo realizar· la transformaciÛn si es de 16
     * bytes.
     * @return devuelve un array con la clave de 24 bytes.
     * @throws Exception
     * si la clave recibida es nula, se lanzar· la excepciÛn.
     * @.doc
     */
    @Throws(Exception::class)
    private fun dameClave3DES(claveMAC: ByteArray): ByteArray {
        val datosPadeo = ByteArrayOutputStream()
        if (claveMAC.size == TAM_CLAVE_3DES) {
            datosPadeo.write(claveMAC, 0, TAM_CLAVE_DES)
            datosPadeo.write(claveMAC, TAM_CLAVE_DES, TAM_CLAVE_DES)
            datosPadeo.write(claveMAC, 0, TAM_CLAVE_DES)
            datosPadeo.flush()
        } else {
            datosPadeo.write(claveMAC)
        }
        return datosPadeo.toByteArray()
    }

    /**
     * Gen clave des.
     *
     * @param claveDes
     * the clave des
     * @return the secret key
     * @.doc
     */
    private fun genClaveDES(claveDes: ByteArray): SecretKey? {
        var oKey3DES: SecretKey? = null
        var desEdeFactory: SecretKeyFactory? = null
        var keyspec: DESedeKeySpec? = null
        try {
            keyspec = DESedeKeySpec(claveDes)
            desEdeFactory = SecretKeyFactory.getInstance("DESede")
            oKey3DES = desEdeFactory.generateSecret(keyspec)
        } catch (e: InvalidKeyException) {
            println(
                "TripleDES.getClaveDES(). La clave Especificada no es valida: "
                        + e
            )
        } catch (e: NoSuchAlgorithmException) {
            println(
                "TripleDES.getClaveDES().El Proveedor especificado en java.security no soporta este algoritmo: "
                        + e.message
            )
        } catch (e: InvalidKeySpecException) {
            // Esta excepciÛn de deberÌa producirse, se captura por si ocurriese
            // alg˙n error interno.
            println(
                "TripleDES.getClaveDES().Error del sistema: "
                        + e
            )
        }
        return oKey3DES
    }
}