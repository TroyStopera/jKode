package com.troystopera.jkode

class Scope(val parent: Scope? = null) {

    fun newChildScope(): Scope = Scope(this)

}