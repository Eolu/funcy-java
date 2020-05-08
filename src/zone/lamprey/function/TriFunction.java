/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published by
 * the Free Software Foundation. Oracle designates this particular file as
 * subject to the "Classpath" exception as provided by Oracle in the LICENSE
 * file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License version 2 for more
 * details (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version 2
 * along with this work; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA or
 * visit www.oracle.com if you need additional information or have any
 * questions.
 */
package zone.lamprey.function;

import java.util.Objects;

/**
 * Represents a function that accepts three arguments and produces a result.
 * This is the three-arity specialization of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <V> the type of the third argument to the function
 * @param <R> the type of the result of the function
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    R apply(T t, U u, V v);
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    default Supplier<R> applyPartial(T t, U u, V v) {
        return () -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    default Function<V, R> applyPartialLM(T t, U u) {
        return v -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param v the third function argument
     * @return the function result
     */
    default Function<U, R> applyPartialLR(T t, V v) {
        return u -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @param v the third function argument
     * @return the function result
     */
    default Function<T, R> applyPartialMR(U u, V v) {
        return t -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @return the function result
     */
    default BiFunction<V, U, R> applyPartialL(T t) {
        return (v, u) -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the second function argument
     * @return the function result
     */
    default BiFunction<T, V, R> applyPartialM(U u) {
        return (t, v) -> apply(t, u, v);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param v the third function argument
     * @return the function result
     */
    default BiFunction<T, U, R> applyPartialR(V v) {
        return (t, u) -> apply(t, u, v);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    default TriConsumer<T, U, V> consume(Consumer<R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(apply(t, u, v));
    }
    
    /**
     * Lift a function.
     * 
     * @param <S> The return type.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <S> TriFunction<T, U, V, S> map(Function<? super R, ? extends S> functor) {
        Objects.requireNonNull(functor);
        return (t, u, v) -> functor.apply(apply(t, u, v));
    }
}
