package com.troystopera.jkode.exec

class Scope(val parent: Scope? = null) {

    fun newChildScope(): Scope = Scope(this)

}