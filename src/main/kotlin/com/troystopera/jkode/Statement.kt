package com.troystopera.jkode

import com.troystopera.jkode.exec.*

abstract class Statement : Executable<Unit>() {

    abstract override fun onExecute(scope: Scope, output: MutableOutput?, executor: Executor?)

}