package com.troystopera.jkode

import com.troystopera.jkode.exec.*

/**
 * Abstract class that represents an [Executable] which makes changes to the [Scope] but does not return anything.
 */
abstract class Statement : Executable<Unit>()