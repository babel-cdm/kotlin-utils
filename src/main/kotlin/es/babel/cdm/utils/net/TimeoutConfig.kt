package es.babel.cdm.utils.net

data class TimeoutConfig(
    val connectTimeoutInSeconds: Long,
    val readTimeoutInSeconds: Long,
    val writeTimeoutInSeconds: Long
)
