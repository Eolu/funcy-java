# FuncyJava

This is a minor rewrite of the java.util.function package with a few more useful
tools and features. It extends all of the interfaces in the original package to 
ensure compatibility. Most additions are default method additions. Here are the 
most significant features:

# mapping functions

Functions, Suppliers, and all the rest of the value-returning functional
interfaces were given mapping methods in the same vein as those used in java 
streams. So `map`, `mapToInt`, `mapToDouble`, `mapToLong`, `mapToObj` (each 
where applicable). They always prefer to give you the most specific functional
interface type. For instance, calling `mapToInt` on a `LongFunction` will return
a `LongToIntFunction`. There's also a `predicate` function which is the same, it 
does the equivalent of a mapToBool and returns a predicate object. Here's some
code:

```
// Starting with a simple function
IntUnaryOperator intMinusTwoAbs = i -> Math.abs(i - 2);

// We can turn it into a predicate
IntPredicate isZero = intMinusTwo.predicate(i -> i == 0);

// And then turn that into an IntFunction<String>.
IntFunction<String> tellZero = isZero.map(isZero -> isZero ? "ZERO" 
                                                           : "MORE THAN ZERO")

```

# interface inheritance heirarchy

To make sure you can still use these types in places where you need the more
generic object-versions, the interfaces now follow an inheritance tree. So
a `LongToIntFunction` is also a `Function<Long, Int>`, a `LongFunction<Integer>`
and a `ToIntFunction<Long>`. You can access the primtive version applyAsInt,
and the more generic version apply. Calling the generic version will simply call
the primitive version and let autoboxing take care of the rest.

# functional partial applications

Another set of default methods added are called applyPartial. These let you pass
in all or some the of arguments that a function accepts to produce a new
function with less parameters. Here are a few examples:

```
// We have a simple int -> String function.
IntFunction<String> tellZero = i -> i == 0 ? "ZERO" 
                                           : "MORE THAN ZERO";

// Technically we've given this function everything it needs to complete this
// computation, but that computation won't occur until we call get()
Supplier<String> howsAboutSeven = tellZero.applyPartial(7);

// So let's do it
String answer = howsAboutSeven.get();
System.out.println(answer);

// And we get this:
--- MORE THAN ZERO

// It's more interesting with the Biconsumer, BiFunction, and TriFunction 
// interfaces.
BiConsumer<PrintStream, String> printLine = PrintStream::println;
Consumer<String> sysOut = printLine.applyPartialL(System.out);
sysOut.accept("Mucho Gracias!");

-- Mucho Gracias!

// We can take it further too:
Runnable thanksOut = sysOut.applyPartial("Mucho Gracias!");
thanksOut.run()

-- Mucho Gracias!

// There are additions to the Runnable class as well
Runnable thanksALot = thanksOut.whileTrue(() -> Math.random() > 0.10);
thanksALot.run();

-- Mucho Gracias!
-- Mucho Gracias!
-- Mucho Gracias!
-- Mucho Gracias!
-- Mucho Gracias!
-- Mucho Gracias!

// We could have done all of these transformations and more in a block:
printLine.applyPartialR("Muy Bien!")
         .map(s -> s + "!!")
         .applyPartial(System.out)
         .whileTrue(() -> Math.random() > 0.45)
         .run()

-- Mucho Gracias!
-- Mucho Gracias!

```

There's also a `consume` function which is very similar to map except it returns
a non-value-returning functional interface:

```

// A Function can be turned into a Consumer.
Function<Object, String> toString = Object::toString;
Consumer<Object> outPrint = toString.consume(s -> System.out.println(s));

// A Supplier can be turned into a Runnable
DoubleSupplier random = Math::random;
random = random.map(d -> d * 10);
Supplier<String> message = random.mapToObj(d -> d + "!!!");
Runnable msgPrint = random.consume(s -> System.out.println(s));

-- 8.746834316796035!!!
```

There's also a utility class called Functions with various useful functions.
