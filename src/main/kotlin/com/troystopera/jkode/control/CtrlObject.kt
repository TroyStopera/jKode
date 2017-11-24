package com.troystopera.jkode.control

data class CtrlObject<out T : Any>(val type: CtrlType, val value: T)