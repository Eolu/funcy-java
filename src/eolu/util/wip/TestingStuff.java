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
package eolu.util.wip;

import java.io.PrintStream;
import java.util.stream.DoubleStream;

import eolu.util.function.BiConsumer;
import eolu.util.function.DoubleBinaryOperator;
import eolu.util.function.DoubleFunction;
import eolu.util.function.DoubleSupplier;

/**
 * This class contains various tests and showcase code.
 */
public class TestingStuff {
    
    static final DoubleBinaryOperator            MAGNITUDE     = (x, y) -> Math.sqrt((x * x) + (y * y));
    static final DoubleFunction<String>          FORMAT_D2     = d -> String.format("%.2f", d);
    static final BiConsumer<PrintStream, String> PRINTLN       = PrintStream::println;
    static final DoubleSupplier                  RANDOM        = Math::random;
    static final DoubleSupplier                  CUSTOM_RANDOM = RANDOM.map(val -> val * 9)
                                                                       .map(Math::exp)
                                                                       .map(val -> val - 457)
                                                                       .map(Math::round)
                                                                       .map(val -> val / 2)
                                                                       .map(val -> val + 10000);
    
    public static void main(String... args) {
        
        double seed = CUSTOM_RANDOM.getAsDouble();
        
        // Here's an example of using these interfaces with a Java Stream.
        DoubleStream.generate(CUSTOM_RANDOM)
                    .map(MAGNITUDE.applyPartialL(seed))
                    .map(d -> 7 * d)
                    .map(d -> d * 100)
                    .map(d -> d * 0.001)
                    .mapToObj(FORMAT_D2)
                    .map(s -> "Wow, " + s + " is quite a number!")
                    .limit(3)
                    .forEach(PRINTLN.applyPartialL(System.out));
        
        // And here's an example of exactly the same computation using only the
        // functional interface default methods.
        CUSTOM_RANDOM.map(MAGNITUDE.applyPartialL(seed))
                     .map(d -> 7 * d)
                     .map(d -> d * 100)
                     .map(d -> d * 0.001)
                     .mapToObj(FORMAT_D2)
                     .map(s -> "Wow, " + s + " is quite a number!")
                     .consume(PRINTLN.applyPartialL(System.out))
                     .loopFor(3)
                     .run();
    }
}