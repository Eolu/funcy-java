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
     * Like {@link #andThen(Runnable)}, but creates a Supplier.
     * 
     * @param <T> The type to return.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, F extends Supplier<T>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (Supplier<T>) () -> {
            run();
            return after.get();
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a Consumer.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, F extends Consumer<T>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (Consumer<T>) t -> {
            run();
            after.accept(t);
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a BiConsumer.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, U, F extends BiConsumer<T, U>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (BiConsumer<T, U>) (t, u) -> {
            run();
            after.accept(t, u);
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a TriConsumer.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, U, V, F extends TriConsumer<T, U, V>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (TriConsumer<T, U, V>) (t, u, v) -> {
            run();
            after.accept(t, u, v);
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a Function.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, R, F extends Function<T, R>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (Function<T, R>) t -> {
            run();
            return after.apply(t);
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a BiFunction.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, U, R, F extends BiFunction<T, U, R>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (BiFunction<T, U, R>) (t, u) -> {
            run();
            return after.apply(t, u);
        };
    }
    
    /**
     * Like {@link #andThen(Runnable)}, but creates a TriFunction.
     * 
     * @param <T> The type to accept.
     * @param after the operation to perform after this operation
     * @return a composed {@code Runnable} that performs in sequence this operation
     *         followed by the {@code after} operation
     * @see {@link #andThen(Runnable)}
     */
    @SuppressWarnings("unchecked")
    default <T, U, V, R, F extends TriFunction<T, U, V, R>> F andThen(F after) {
        Objects.requireNonNull(after);
        return (F) (TriFunction<T, U, V, R>) (t, u, v) -> {
            run();
            return after.apply(t, u, v);
        };
    }
    
    /**
     * @return A version of this Runnable that calls the {@link #run} method
     *         repeatedly forever. This will never terminate unexceptionally.
     */
    default Runnable forever() {
        return () -> {
            for (;;) run();
        };
    }
    
    /**
     * @param times The number of times to loop.
     * @return A version of this Runnable that calls the {@link #run} method a given
     *         number of times
     */
    default Runnable loopFor(int times) {
        return () -> {
            for (int i = 0; i < times; i++) run();
        };
    }
    
    /**
     * @param terminationCondition The condition upon which to terminate.
     * @return A version of this Runnable that calls the {@link #run} method
     *         repeatedly until the given BooleanSupplier returns false. If the
     *         BooleanSupplier returns false on its initial test, the {@link #run}
     *         method will never be called.
     */
    default Runnable whileTrue(BooleanSupplier terminationCondition) {
        Objects.requireNonNull(terminationCondition);
        return () -> {
            while (terminationCondition.getAsBoolean()) run();
        };
    }
}
