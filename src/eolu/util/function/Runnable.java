/*
 * Copyright 2019 Griffin O'Neill
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
package eolu.util.function;

import java.util.Objects;

/**
 * This is a functional interface that accepts nothing and returns nothing.
 */
@FunctionalInterface
public interface Runnable extends java.lang.Runnable {
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public abstract void run();
    
    /**
     * Returns a composed {@code Runnable} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Runnable andThen(Runnable after) {
        Objects.requireNonNull(after);
        return () -> {
            run();
            after.run();
        };
    }
    
    /**
     * @return A version of this Runnable that calls the {@link #run} method
     *         repeatedly forever. This will never terminate unexceptionally.
     */
    default Runnable forever() {
        return () -> {
            for (;;)
                run();
        };
    }
    
    /**
     * @param times The number of times to loop.
     * @return A version of this Runnable that calls the {@link #run} method a given
     *         number of times
     */
    default Runnable loopFor(int times) {
        return () -> {
            for (int i = 0; i < times; i++)
                run();
        };
    }
    
    /**
     * @param times The number of times to loop.
     * @return A version of this Runnable that calls the {@link #run} method
     *         repeatedly until the given BooleanSupplier returns false. If the
     *         BooleanSupplier returns false on its initial test, the {@link #run}
     *         method will never be called.
     */
    default Runnable whileTrue(BooleanSupplier terminationCondition) {
        Objects.requireNonNull(terminationCondition);
        return () -> {
            while (terminationCondition.getAsBoolean())
                run();
        };
    }
}
