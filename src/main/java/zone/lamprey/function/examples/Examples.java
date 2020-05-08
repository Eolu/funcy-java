/*
 * Copyright 2018 Griffin O'Neill
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package zone.lamprey.function.examples;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import zone.lamprey.function.BiConsumer;
import zone.lamprey.function.Consumer;
import zone.lamprey.function.DoubleBinaryOperator;
import zone.lamprey.function.DoubleFunction;
import zone.lamprey.function.DoubleSupplier;
import zone.lamprey.function.ToIntFunction;

/**
 * This class contains various tests and showcase code.
 */
public class Examples {
    
    static final int                             TEST_ITERATIONS = 100000;
    static final ToIntFunction<List<Integer>>    SUM_LIST        = c -> c.stream().mapToInt(i -> i).sum();
    static final DoubleBinaryOperator            MAGNITUDE       = (x, y) -> Math.sqrt((x * x) + (y * y));
    static final DoubleFunction<String>          FORMAT_D2       = d -> String.format("%.2f", d);
    static final BiConsumer<PrintStream, String> PRINTLN         = PrintStream::println;
    static final DoubleSupplier                  CUSTOM_RANDOM   = ((DoubleSupplier) Math::random).map(val -> val * 9)
                                                                                                  .map(Math::exp)
                                                                                                  .map(val -> val - 457)
                                                                                                  .map(Math::round)
                                                                                                  .map(val -> val / 2)
                                                                                                  .map(val -> val + 10000);
    
    public static void main(String... args) {
        double seed = CUSTOM_RANDOM.getAsDouble();
        Consumer<String> toStdOut = PRINTLN.applyPartialL(System.out);
        List<Integer> streams = new ArrayList<>(TEST_ITERATIONS);
        List<Integer> funcs = new ArrayList<>(TEST_ITERATIONS);
        List<Integer> iters = new ArrayList<>(TEST_ITERATIONS);
        
        // Run both tests a bunch of times first to give the JIT compiler time to think
        for (int i = 0; i < TEST_ITERATIONS / 1000; i++) {
            toStdOut.accept("Running test " + i + " of " + (TEST_ITERATIONS / 1000));
            streams.add(streamTest(seed).size());
            funcs.add(funcTest(seed).size());
            iters.add(iterTest(seed).size());
        }
        toStdOut.accept("\n --- Total Iterations: " + SUM_LIST.applyAsInt(streams) + " --- \n");
        
        // Do one more to show off the result
        CUSTOM_RANDOM.map(MAGNITUDE.applyPartialL(seed))
                     .map(d -> 7 * d)
                     .map(d -> d * 100)
                     .map(d -> d * 0.001)
                     .mapToObj(FORMAT_D2)
                     .map(s -> "Wow, " + s + " is quite a number!")
                     .consume(toStdOut)
                     .loopFor(3)
                     .run();
    }
    
    /**
     * Here's an example of using these interfaces with a Java Stream.
     * 
     * @return The time it took to run, in nanoseconds.
     */
    private static List<String> streamTest(double seed) {
        List<String> container = new ArrayList<>(TEST_ITERATIONS);
        // Perform the actual computation
        DoubleStream.generate(CUSTOM_RANDOM)
                    .map(MAGNITUDE.applyPartialL(seed))
                    .map(d -> 7 * d)
                    .map(d -> d * 100)
                    .map(d -> d * 0.001)
                    .mapToObj(FORMAT_D2)
                    .map(s -> "Wow, " + s + " is quite a number!")
                    .limit(TEST_ITERATIONS)
                    .forEach(container::add);
        return container;
    }
    
    /**
     * And here's an example of exactly the same computation using only the
     * functional interface default methods.
     * 
     * @return The time it took to run, in nanoseconds.
     */
    private static List<String> funcTest(double seed) {
        List<String> container = new ArrayList<>(TEST_ITERATIONS);
        // Perform the actual computation
        CUSTOM_RANDOM.map(MAGNITUDE.applyPartialL(seed))
                     .map(d -> 7 * d)
                     .map(d -> d * 100)
                     .map(d -> d * 0.001)
                     .mapToObj(FORMAT_D2)
                     .map(s -> "Wow, " + s + " is quite a number!")
                     .consume(container::add)
                     .loopFor(TEST_ITERATIONS)
                     .run();
        return container;
    }
    
    /**
     * And here's an example of exactly the same computation using common java
     * iteration practices.
     * 
     * @return The time it took to run, in nanoseconds.
     */
    private static List<String> iterTest(double seed) {
        List<String> container = new ArrayList<>(TEST_ITERATIONS);
        // Perform the actual computation
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            double d = MAGNITUDE.applyAsDouble(seed, CUSTOM_RANDOM.get());
            d = 7 * d;
            d = d * 100;
            d = d * 0.001;
            String s = FORMAT_D2.apply(d);
            s = "Wow, " + s + " is quite a number!";
            container.add(s);
        }
        return container;
    }
}