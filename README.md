# jKode
#### Model and execute virtual code

This project was initially part of my senior design project, GenCODE, which aimed to generate code samples for computer science education. I have continued work on GenCODE but decided to split jKode into a seperate project both for an easier-to-maintain codebase and in the hopes it may help others.

### Overview
#### Key features
 * Use components, statements, and evaluations like building blocks to model any basic piece of code
 * Quickly execute virtual code at runtime
 * Easily listen to and override virtual execution
 * Designed to be easily modified and built upon
 * Format virtual code models to text

#### Summary
What makes jKode unique is its ability to listen to, and override, virtual execution of code through the use of the [OverrideExecutor](https://github.com/TroyStopera/jKode/blob/master/src/main/kotlin/com/troystopera/jkode/exec/override/OverrideExecutor.kt). This can be used to simulate errors in students' mental execution of a program, or to simulate bugs in code. All output and return values are written to an [Output](https://github.com/TroyStopera/jKode/blob/master/src/main/kotlin/com/troystopera/jkode/exec/Output.kt), which can then be used to analyze the code. When executing with the [Executor](https://github.com/TroyStopera/jKode/blob/master/src/main/kotlin/com/troystopera/jkode/exec/Executor.kt), any compile or runtime errors the modeled code may throw will also be logged to the Output.


### Example
This code will create and execute a basic function with a for loop and int return:

```kotlin
//create a function named example that returns an int
val func = JFunction(VarType.INT, "example")

//add an int var with initial value of 0 and a blank line
func.body.add(Declaration(VarType.INT, "data", IntVar[0].eval))
func.body.add(BlankLine)

//create loop declaration 'int i = 0'
val declaration = Declaration(
  VarType.INT, /* variable type */
  "i", /* variable name */
  IntVar[0].eval /* initial value */
)
        
//create loop condition 'i < 10'
val condition = Comparison(
  Comparison.Type.LESS_THAN, /* comparison operator */
  Variable(VarType.INT, "i"), /* left side of comparison is variable i */
  IntVar[10].eval /* right side is int literal 10 */
)
        
//create loop increment 'i = i + 1'
val increment = Assignment(
  "i", /* variable name */
  MathOperation( /* assigning i to the result of a math operation */
    MathOperation.Type.ADD, /* math operator */
    Variable(VarType.INT, "i"), /* left side of operation */
    IntVar[1].eval /* right side of operation */
  )
)

//create the for loop with the above parts
val loop = ForLoop(declaration, condition, increment)

//add a manipulation to 'data'
loop.add(Assignment("data", Variable(VarType.INT, "i")))
        
//add another blank line and a return to the function
func.body.add(BlankLine)
func.body.add(Return(Variable(VarType.INT, "data")))
        
//execute the function
val output = Executor().execute(func)
```

When formatted as Java, the virtual code created above would look like this:
```java
public int example() {
  int data = 0;
  
  for (int i = 0; i < 10; i = i + 1) {
    data = i;
  }

  return data;
}
```


Although this code does nothing of significance, jKode can easily represent much for purposeful and complex code.
