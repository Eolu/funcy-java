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
 * Represents a function that produces an int-valued result. This is the
 * {@code int}-producing primitive specialization for {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsInt(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface ToIntFunction<T> extends Function<T, Integer>, java.util.function.ToIntFunction<T> {
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    int applyAsInt(T value);
    
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Integer apply(T t) {
        return applyAsInt(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    @Override
    default IntSupplier applyPartial(T t) {
        return () -> apply(t);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    default Consumer<T> consume(IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToIntFunction<T> map(IntUnaryOperator functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsInt(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <S> The return type.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <S> Function<T, S> mapToObj(IntFunction<? extends S> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default Predicate<T> mapToPredicate(IntPredicate functor) {
        Objects.requireNonNull(functor);
        return t -> functor.test(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToDoubleFunction<T> mapToDouble(IntToDoubleFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsDouble(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToLongFunction<T> mapToLong(IntToLongFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsLong(applyAsInt(t));
    }
}
