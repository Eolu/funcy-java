/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
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
package eolu.util.function;

import java.util.Objects;

/**
 * Represents a function that accepts a double-valued argument and produces a
 * result. This is the {@code double}-consuming primitive specialization for
 * {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #apply(double)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface DoubleFunction<R> extends Function<Double, R>, java.util.function.DoubleFunction<R> {
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    R apply(double value);
    
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default R apply(Double t) {
        return apply(t.doubleValue());
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    default Supplier<R> applyPartial(double t) {
        return () -> apply(t);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    @Override
    default DoubleConsumer consume(Consumer<R> consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(apply(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <S> The return type.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default <S> DoubleFunction<S> map(Function<? super R, ? extends S> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoublePredicate mapToPredicate(Predicate<? super R> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.test(apply(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleUnaryOperator mapToDouble(ToDoubleFunction<? super R> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsDouble(apply(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleToIntFunction mapToInt(ToIntFunction<? super R> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsInt(apply(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleToLongFunction mapToLong(ToLongFunction<? super R> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsLong(apply(t));
    }
    
    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static DoubleUnaryOperator identity() {
        return t -> t;
    }
}
