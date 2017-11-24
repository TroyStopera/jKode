package com.troystopera.jkode.exceptions

abstract class JKodeException(override val message: String, override val cause: Exception?) : Exception()