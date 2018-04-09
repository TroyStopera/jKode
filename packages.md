# Package com.troystopera.jkode

Base package for jKode project containing base implementations for all Executables.

# Package com.troystopera.jkode.components

Implementations of the [Component] class; these classes represent basic compound blocks of code and may return [CtrlStmt]. 

# Package com.troystopera.jkode.control

Classes used to interrupt execution of code.

# Package com.troystopera.jkode.evaluations

Implementations of the [Evaluation] class; these classes return a value during execution of code.

# Package com.troystopera.jkode.exceptions

Contains exceptions that may be "thrown" during virtual execution of code.

# Package com.troystopera.jkode.exceptions.compile

Implementations of the [JKodeCompileException] class; exceptions that would normally be thrown at compile time.

These exceptions include anything that would prevent a compiler from compiling, such as unknown variable references and improper syntax. 

# Package com.troystopera.jkode.exceptions.runtime

Implementations of the [JKodeRuntimeException] class; exceptions that would normally be thrown at runtime.

These exceptions include anything that would be thrown during code execution, such as IndexOutOfBounds and NullPointer. 

# Package com.troystopera.jkode.exec

Contains classes relating to the virtual runtime/execution of code.

# Package com.troystopera.jkode.exec.override

Contains classes used for overriding virtual execution at runtime.

# Package com.troystopera.jkode.format

Contains classes for the formatting of virtual code into a human readable String.

# Package com.troystopera.jkode.statements

Implementations of the [Statement] class; these classes alter values at virtual runtime, but return nothing.

# Package com.troystopera.jkode.vars

Contains classes used in the representation of virtual variables.
