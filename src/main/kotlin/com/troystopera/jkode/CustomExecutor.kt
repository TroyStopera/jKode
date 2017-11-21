package com.troystopera.jkode

import com.troystopera.jkode.exec.*

class CustomExecutor : ExecutionWatcher, Executor() {

    private val overrides = hashMapOf<String, OverrideNode<*>>()
    private val cache = hashMapOf<String, ExecutionOverride<*>?>()

    override fun execute(executable: Executable): Output {
        val output = ExecOutput()
        val scope = Scope()
        executable.execute(scope, output, null, this)
        return output
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E : Executable> getOverride(executable: E, caller: Caller?): ExecutionOverride<E>? {
        val cacheKey = CallStack.execToString(executable) + CallStack.callerToString(caller)
        if (cache.containsKey(cacheKey)) return cache[cacheKey] as ExecutionOverride<E>?

        var override: ExecutionOverride<E>? = null
        var c = caller
        var node = overrides[CallStack.execToString(executable)]

        while (node != null && c != null) {
            if (node.override != null)
                override = node.override as ExecutionOverride<E>?
            node = node.callers[CallStack.execToString(c.executable)]
            c = c.parentCaller
        }

        cache.put(cacheKey, override)
        return override
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : Executable> addOverride(callStack: CallStack<E>, override: ExecutionOverride<E>) {
        var node: OverrideNode<E> = overrides[callStack.base] as OverrideNode<E>? ?: OverrideNode()
        for (s in callStack.callers)
            node = node.getOrCreate(s)
        node.override = override
        cache.remove(callStack.toString())
    }

    private inner class OverrideNode<E : Executable> {

        var override: ExecutionOverride<E>? = null

        val callers = hashMapOf<String, OverrideNode<E>>()

        fun getOrCreate(string: String): OverrideNode<E> = callers[string] ?: OverrideNode()

    }

}