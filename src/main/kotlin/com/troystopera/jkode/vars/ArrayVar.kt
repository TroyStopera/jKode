package com.troystopera.jkode.vars

class ArrayVar<T : Var<*>>(size: Int) : Var<Array<T?>>(VarType.ARRAY, arrayOfNulls<T>(size))