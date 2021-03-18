package es.babel.cdm.utils.android.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class Encryptor {

    private val secretKey = getKey()
    private val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)

    fun encrypt(value: String): Model {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return Model(
            bytes = cipher.doFinal(value.toByteArray(Charsets.UTF_8)),
            iv = cipher.iv
        )
    }

    fun decrypt(value: Model): String? {
        return runCatching {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(value.iv))
            String(cipher.doFinal(value.bytes), Charsets.UTF_8)
        }.getOrNull()
    }

    private fun getKey(): SecretKey {
        val keyStore = KeyStore.getInstance(KEY_PROVIDER)
        keyStore.load(null)
        return keyStore.getKey(KEY_ALIAS, null)?.let { key -> key as SecretKey }
            ?: generateSecretKey()
    }

    private fun generateSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, KEY_PROVIDER
        )

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    data class Model(
        val bytes: ByteArray,
        val iv: ByteArray
    )

    companion object {
        const val KEY_PROVIDER = "AndroidKeyStore"
        const val KEY_ALIAS = "KeyAlias"
        const val CIPHER_TRANSFORMATION = "${KeyProperties.KEY_ALGORITHM_AES}/" +
            "${KeyProperties.BLOCK_MODE_CBC}/" +
            KeyProperties.ENCRYPTION_PADDING_PKCS7
    }
}
