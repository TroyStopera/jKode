package com.troystopera.jkode

import com.troystopera.jkode.exec.Executable

/**
 * Abstract class that represents a grouping of code that can be executed.
 *
 * These components are complex expressions (such as Loops) that execute in a very specific way.
 */
abstract class Component : Executable<CtrlStmt<*>?>()