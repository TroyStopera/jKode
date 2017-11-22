package com.troystopera.jkode.exec.override

import com.troystopera.jkode.exec.*

class OverrideExecutor : ExecutionWatcher, Executor() {

    override val callStack = MutableCallStack()

    private val overrides = hashMapOf<String, OverrideNode<*>>()
    private val cache = hashMapOf<String, ExecutionOverride<*>?>()

    override fun execute(executable: Executable): Output {
        val output = ExecOutput()
        val scope = Scope()
        executable.execute(scope, output,this)
        return output
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E : Executable> getOverride(callStack: CallStack): ExecutionOverride<E>? {
        val cacheKey = callStack.toString()
        if (cache.containsKey(cacheKey)) return cache[cacheKey] as ExecutionOverride<E>?

        var override: ExecutionOverride<E>? = null
        var node = overrides[callStack.getBase()]
        val iterator = callStack.getCallers().iterator()

        while (node != null && iterator.hasNext()) {
            if (node.override != null)
                override = node.override as ExecutionOverride<E>?
            node = node.callers[iterator.next()]
        }

        override = node?.override as? ExecutionOverride<E> ?: override
        cache.put(cacheKey, override)
        return override
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : Executable> addOverride(watchable: Watchable<E>, override: ExecutionOverride<E>) {
        var node: OverrideNode<E> = overrides[watchable.getBase()] as OverrideNode<E>? ?: {
            val node = OverrideNode<E>()
            overrides.put(watchable.getBase(), node)
            node
        }.invoke()
        for (s in watchable.getCallers())
            node = node.getOrCreate(s)
        node.override = override
        cache.clear()
    }

    private inner class OverrideNode<E : Executable> {

        var override: ExecutionOverride<E>? = null

        val callers = hashMapOf<String, OverrideNode<E>>()

        fun getOrCreate(string: String): OverrideNode<E> = callers[string] ?: OverrideNode()

    }

}