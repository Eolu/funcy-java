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
package zone.lamprey.function;

import java.util.Objects;

/**
 * Represents a function that accepts two arguments and produces an int-valued
 * result. This is the {@code int}-producing primitive specialization for
 * {@link BiFunction}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsInt(Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 *
 * @see BiFunction
 * @since 1.8
 */
@FunctionalInterface
public interface ToIntBiFunction<T, U> extends BiFunction<T, U, Integer>, java.util.function.ToIntBiFunction<T, U> {
    
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    int applyAsInt(T t, U u);
    
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default Integer apply(T t, U u) {
        return applyAsInt(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default IntSupplier applyPartial(T t, U u) {
        return () -> applyAsInt(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default ToIntFunction<U> applyPartialL(T t) {
        return u -> applyAsInt(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the function argument
     * @return the function result
     */
    @Override
    default ToIntFunction<T> applyPartialR(U u) {
        return t -> applyAsInt(t, u);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    default BiConsumer<T, U> consume(IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.accept(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToIntBiFunction<T, U> map(IntUnaryOperator functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.applyAsInt(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     * 
     * @param <S> The return type.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <S> BiFunction<T, U, S> mapToObj(IntFunction<? extends S> functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.apply(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default BiPredicate<T, U> mapToPredicate(IntPredicate functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.test(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToDoubleBiFunction<T, U> mapToDouble(IntToDoubleFunction functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.applyAsDouble(applyAsInt(t, u));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default ToLongBiFunction<T, U> mapToLong(IntToLongFunction functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.applyAsLong(applyAsInt(t, u));
    }
}
