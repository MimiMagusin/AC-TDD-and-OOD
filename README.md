# Test Driven Development & OO Design

>In this class we will discuss TDD, using some of the SOLID principles as guidelines. The topics include:
>
> * Best practices in projects (feedback, code reviews)
> * Test Driven Development
> * Object Oriented design (_Liskov and Interface_)

In this README you can find...
* A list of concepts
* The SOLID principles
* An explanation of TDD
* Intro to Kotlin/IJ
* Interesting sources

<hr/>

## Concepts

### Polymorphism
- The word ['polymorphism'](https://www.sitepoint.com/quick-guide-to-polymorphism-in-java/) literally means 'a state of having many shapes' or 'the capacity to take on different forms'. When applied to object oriented programming languages like Java, it describes a language's ability to _process objects of various types and classes through a single, uniform **interface**_.

### Classes
* An object is an instance of a class
* Class is the definition of something (anything!) that you can create.

**or**
* class: how data should look
* object: data with _pointer_ to it

> **Passing by reference:** passing data locations rather than actual data

### Typing

SuperType (with methods)
Subtype inherits from SuperType

![Classes & Subclasses](https://docs.oracle.com/en/database/oracle/oracle-database/12.2/adobj/img/adobj027.gif)

Liskov principle: in a method like `m(Ax)` `A` should be replacable by `B`,`C`, or `D`

Try not to have more than one superclass. 

### Composition Over Inheritance


### Interface
- An [interface](https://beginnersbook.com/2013/05/java-interface/) looks like a class but it is not a class. _An interface can have methods and variables just like the class but the methods declared in interface are by default abstract_ (only method signature, no body).
-  In [Kotlin](https://kotlinlang.org/docs/reference/interfaces.html):
```Kotlin
//Declare an interface
interface MyInterface {
    val prop: Int 
    fun bar()
    fun foo() 
}

//implement that interface
class Child : MyInterface {
  override fun bar() {
      // body
  }

  override val prop: Int = 29
}
```
> In kotlin it is possible to implement bodies of classes.

<hr/>

## SOLID
![Solid Explanation](http://wall-skills.com/wp-content/uploads/2013/12/solid-OOP_wall-skills.jpg)


## Liskov Substitution principle
  - **Definition**
    - Child classes should never break teh parent class' type definitions.
    - Subtypes must be substitutable for their base types.
  _ **Why?**
  ![Rectangle Square Problem](https://cdn.tutsplus.com/net/uploads/2014/01/SquareRect.png)
    - Checkout the rectangle square problem: a square might seem to be an implementation of a but it isn't! [**More info**](https://code.tutsplus.com/tutorials/solid-part-3-liskov-substitution-interface-segregation-principles--net-36710)

## Interface segregation principle
> _small cohesive interfaces are the name of the game_
- **Definition:**
    - _Clients should not be forced to depend upon interfaces that they do not use._
- **example**
    - If you'd have an interface with multiple methods but not all are used, split your interface in multiple interfaces!

<hr/>

## TDD
### Three rules
  1. You are not allowed to write any production code unless it is to make a failing unit test pass.
  2. You are note allowed to write any more of a unit test than is sufficient to fail, and compilation failures are failures
  3. You are not allowed to write any more production code than is sufficient to pass the on failing unit test.

### Process
![TDD process](http://lewandowski.io/images/tdd_flow.gif)
Continuously zooming in and out, writing tests that fails, writing code to make it pass (according to the rules above), refactor your code.

### Example
Build a calculator that can implement the functions sum and multiply
* First step: 
```kotlin
class MyTests {

  @Test
  fun smokeTest() {
    //Arrange
    val calculator : Calculator = Calculator()
    //Act
    val sum =calculator.sum(1,2)
    //Assert
    Assertions.assertThat(sum).isEqualTo(3)
  }
}
```
Will not be able to compile (class Calculator does not exist), so:
```kotlin
class MyTests {

  @Test
  fun smokeTest() {
    //Arrange
    val calculator : Calculator = Calculator()
    //Act
    val sum = calculator.sum(1,2)
    //Assert
    Assertions.assertThat(sum).isEqualTo(3)
  }

  class Calculator{

  }
}
```
still doesn't compile (doesn't know sum)...
```kotlin
class MyTests {

  @Test
  fun smokeTest() {
    //Arrange
    val calculator : Calculator = Calculator()
    //Act
    val sum =calculator.sum(1,2)
    //Assert
    Assertions.assertThat(sum).isEqualTo(3)
  }

  class Calculator{
    fun sum(a: Int, b:Int) {

    }
  }
}
```
now it compiles, test still fails though, so:

```kotlin
class MyTests {

  @Test
  fun smokeTest() {
    //Arrange
    val calculator = Calculator()
    //Act
    val sum =calculator.sum(1,2)
    //Assert
    Assertions.assertThat(sum).isEqualTo(3)
  }

  class Calculator{
    fun sum(a: Int, b: Int) : Int {
      return three
    }
  }
}
```
Cool! Time for a new test:
* **Step 2**
```kotlin
class MyTests {

  @Test
  fun oneAndTwoIsThree() {
      //Arrange
      val calculator : Calculator = Calculator()
      //Act
      val sum = calculator.sum(1, 2)
      //Assert
      Assertions.assertThat(sum).isEqualTo(3)
  }  

  @Test
  fun threeAndFiveIsEight() {
    //Arrange
    val calculator = Calculator()
    //Act
    val sum = calculator.sum(3, 5)
    //Assert
    Assertions.assertThat(sum).isEqualTo(8)
  }

  class Calculator{
    fun sum(a: Int, b:Int) {
      return a + b
    }
  }
}
```

<hr/>
<hr/>

## Using Kotlin & IntelliJ
![Kotlin](https://cdn-images-1.medium.com/max/1600/1*umyUJ29VaESyjiwjW-RW1A.png)The exercises in this repository are written in [Kotlin](http://kotlinlang.org/docs/reference/).
### Why?
- Staticly typed language
- Simple and fun to learn

**Some examples:** 

```kotlin
//@test: Metadata on method or class -> run this, this is a Test method

@Test
fun callSums() {

  // val is like const
  val sum: Int = secondSum(a1, b:2) // explicit type
  val myText = "Yay"  // inferred type

  // var is like let
  var sumB = secondSum(a:3, b:4)
}

fun sum(a: Int, b:Int) : Int {
  return a + b
}


// Same as method above, type is inferred -> The compiler does it for you:
fun secondDum( a: Int, b:Int) = a + b
val myText = "Yay!"


// You can return a Unit ro return nothing (Void)
fun doStuffForMyCaller(a: Int) : Unit {

  //Writes a file

}
```
**Classes**
```kotlin

class MyClassesAndStuffDemo{

  @Test
  fun smokeTest(){

    //Create Instance of class Calculator
    val myCalculator = Calculator(a: 2, b: 2) 

    val theSum(): Int = myCalculator.sum()

  }
}


//(a: Int, b: Int)  = constructor
class Calculator(a: Int, b: Int) {
  // A field
  val valOne:Int = a
  val valTwo:Int = b

  //Each object can perform class methods
  fun sum(): Int {
    return valOne + valTwo
  }
}
```

**Abstract classes**
```Kotlin
//SuperType => You can't instantiate this
abstract class Vehicle {
  var isDriving: Boolean = false
    protected set

  fun drive() {
    isDriving = true
  }
}


//SubType

class Car {
  var isDrifting: Booelan = false
    protected set

  fun drifting() {
    isDrifting = true
  }
}

fun abstractClassInheritance() {

  // Do stuff that a car can do
  val myTestCar : Car = Car()

  // Do only stuff that a vehicle can do
  val aCarVehicleObject: Vehicle = Car()

}


//You can use both the drive and drift methods on class Type
```

**Open classes**
```kotlin
// `open`: other classes can inherit from me
open class rectangle(open var width: Int, open var height: Int) {
  fun area(): Int {
    return width * height
  }
}

class Square(sideLength: Int): Rectangle(width: sideLength, height: sideLength) {
  //return something
}
```
### IntelliJ
![IntelliJ](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/IntelliJ_IDEA_Logo.svg/1200px-IntelliJ_IDEA_Logo.svg.png)
  * Editor
    * Refactors for you
    * Useful short cuts
      * cmd L => Format my code
      * ⇧F6 => Refactor | Rename
<hr />

## Interesting Sources

* [Solid Cheat Sheets](https://www.monterail.com/blog/solid-principles-cheatsheet-printable)
* [Poymorphism](https://www.sitepoint.com/quick-guide-to-polymorphism-in-java/)
* [Liskov Substitution Principle](https://code.tutsplus.com/tutorials/solid-part-3-liskov-substitution-interface-segregation-principles--net-36710)
* [Interfaces](https://beginnersbook.com/2013/05/java-interface/)
* [Kotlin Tutorials](https://kotlinlang.org/docs/tutorials/)
* [Getting Started With Kotlin & IntelliJ](https://kotlinlang.org/docs/tutorials/getting-started.html)


