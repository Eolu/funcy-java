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
package eolu.util.wip;

import java.util.Objects;

import eolu.util.function.BiConsumer;
import eolu.util.function.Consumer;

/**
 * This class is a consumer that takes three arugments.
 * 
 * @param <T> The first parameter.
 * @param <U> The second parameter.
 * @param <V> The third parameter.
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {
    
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param v the third input argument
     */
    public void accept(T t, U u, V v);
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(l, r, p);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenLM(BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(l, r);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenLR(BiConsumer<? super T, ? super V> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(l, p);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenMR(BiConsumer<? super U, ? super V> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(r, p);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenL(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(l);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenM(Consumer<? super U> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(r);
        };
    }
    
    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the
     * {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, V> andThenR(Consumer<? super V> after) {
        Objects.requireNonNull(after);
        
        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(p);
        };
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    default Runnable applyPartial(T t, U u, V v) {
        return () -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    default Consumer<V> applyPartialLM(T t, U u) {
        return v -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param v the third function argument
     * @return the function result
     */
    default Consumer<U> applyPartialLR(T t, V v) {
        return u -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    default Consumer<T> applyPartialMR(U u, V v) {
        return t -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @return the function result
     */
    default BiConsumer<V, U> applyPartialL(T t) {
        return (v, u) -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @return the function result
     */
    default BiConsumer<T, V> applyPartialM(U u) {
        return (t, v) -> accept(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param v the third function argument
     * @return the function result
     */
    default BiConsumer<T, U> applyPartialR(V v) {
        return (t, u) -> accept(t, u, v);
    }
}
