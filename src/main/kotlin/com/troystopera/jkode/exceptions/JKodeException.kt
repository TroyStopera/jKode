package com.troystopera.jkode.exceptions

abstract class JKodeException(msg: String, override val cause: Exception) : Exception(msg + "\n" + cause.message)