package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.*

class OverrideExecutor : Executor() {

    private val callStack = MutableCallStack()

    private val overrides = hashMapOf<String, OverrideNode<*, *>>()
    private val cache = hashMapOf<String, ExecutionOverride<*, *>?>()

    override fun <T, E : Executable<T>> onPreExecute(executable: E): ExecutionOverride<T, E>? {
        callStack.startCall(executable)
        return getOverride(callStack)
    }

    override fun <T, E : Executable<T>> onPostExecute(executable: E, result: T) {
        super.onPostExecute(executable, result)
        callStack.endCall()
    }

    override fun execute(executable: Executable<*>): Output {
        val output = ExecOutput()
        val scope = Scope()
        executable.execute(scope, output, this)
        return output
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any?, E : Executable<T>> addOverride(watchable: Watchable<E>, override: ExecutionOverride<T, E>) {
        var node: OverrideNode<T, E> = overrides[watchable.getBase()] as OverrideNode<T, E>? ?: {
            val node = OverrideNode<T, E>()
            overrides.put(watchable.getBase(), node)
            node
        }.invoke()
        for (s in watchable.getCallers())
            node = node.getOrCreate(s)
        node.override = override
        cache.clear()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any?, E : Executable<T>> getOverride(callStack: CallStack): ExecutionOverride<T, E>? {
        val cacheKey = callStack.toString()
        if (cache.containsKey(cacheKey)) return cache[cacheKey] as ExecutionOverride<T, E>?

        var override: ExecutionOverride<T, E>? = null
        var node = overrides[callStack.getBase()]
        val iterator = callStack.getCallers().iterator()

        while (node != null && iterator.hasNext()) {
            if (node.override != null)
                override = node.override as ExecutionOverride<T, E>?
            node = node.callers[iterator.next()]
        }

        override = node?.override as? ExecutionOverride<T, E> ?: override
        cache.put(cacheKey, override)
        return override
    }

    private inner class OverrideNode<T : Any?, E : Executable<T>> {

        var override: ExecutionOverride<T, E>? = null

        val callers = hashMapOf<String, OverrideNode<T, E>>()

        fun getOrCreate(string: String): OverrideNode<T, E> = callers[string] ?: OverrideNode()

    }

}