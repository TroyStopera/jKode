package com.troystopera.jkode.exceptions.runtime

import com.troystopera.jkode.exceptions.JKodeRuntimeException

class ArrayIndexException(
        size: Int,
        index: Int
) : JKodeRuntimeException(Type.ARRAY_INDEX, "Array index out of bounds exception: size $size index $index")