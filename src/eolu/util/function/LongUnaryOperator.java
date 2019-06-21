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
 * Represents an operation on a single {@code long}-valued operand that produces
 * a {@code long}-valued result. This is the primitive type specialization of
 * {@link UnaryOperator} for {@code long}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsLong(long)}.
 *
 * @see UnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface LongUnaryOperator extends LongFunction<Long>, UnaryOperator<Long>, ToLongFunction<Long> {
    
    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    long applyAsLong(long operand);
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default long applyAsLong(Long value) {
        return applyAsLong(value.longValue());
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Long apply(long value) {
        return applyAsLong(value);
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Long apply(Long value) {
        return applyAsLong(value.longValue());
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    @Override
    default LongSupplier applyPartial(long t) {
        return () -> applyAsLong(t);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    @Override
    default LongConsumer consume(LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <S> The return type.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default <S> LongFunction<S> mapToObj(LongFunction<? extends S> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongPredicate mapToPredicate(LongPredicate functor) {
        Objects.requireNonNull(functor);
        return t -> functor.test(applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongToDoubleFunction mapToDouble(LongToDoubleFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsDouble(applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default LongToIntFunction mapToInt(LongToIntFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsInt(applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default LongUnaryOperator map(UnaryOperator<Long> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsLong(t));
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param terminalCondition The condition upon which the "recursion" will
     *            terminate.
     * @return The result of the recursive call.
     */
    default long recurse(long value, LongPredicate terminalCondition) {
        while (terminalCondition.test(value))
            value = applyAsLong(value);
        return value;
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param terminalCondition The condition upon which the "recursion" will
     *            terminate.
     * @param maxDepth The maximum number of times to recurse. A depth of zero or
     *            less will simply return the passed-in value (the function will
     *            never be called).
     * @return The result of the recursive call.
     */
    default long recurse(long value, LongPredicate terminalCondition, int maxDepth) {
        for (int i = 0; i < maxDepth && terminalCondition.test(value); i++)
            value = applyAsLong(value);
        return value;
    }
    
    /**
     * A method that tells a function to call itself with its own result. This
     * actually imitates recursion using iteration. Don't tell anyone.
     * 
     * @param value The initial value to apply to the function.
     * @param depth The number of times to recurse. A depth of zero or less will
     *            simply return the passed-in value (the function will never be
     *            called).
     * @return The result of the recursive call.
     */
    default long recurse(long value, int depth) {
        for (int i = 0; i < depth; i++)
            value = applyAsLong(value);
        return value;
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * until some condition is met.
     * 
     * @param terminalCondition The condition upon which the recursion will
     *            terminate.
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    default LongUnaryOperator recursive(LongPredicate terminalCondition) {
        Objects.requireNonNull(terminalCondition);
        return t -> recurse(t, terminalCondition);
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * some amount of times or until some condition is met.
     * 
     * @param terminalCondition The condition upon which the recursion will
     *            terminate.
     * @param maxDepth The maximum number of times to recurse. A depth of zero or
     *            less will simply return the passed-in value (the function will
     *            never be called).
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    default LongUnaryOperator recursive(LongPredicate terminalCondition, int maxDepth) {
        Objects.requireNonNull(terminalCondition);
        return t -> recurse(t, terminalCondition, maxDepth);
    }
    
    /**
     * A method that creates a function which calls a function with its own result
     * some amount of times.
     * 
     * @param depth The number of times to recurse. A depth of zero or less will
     *            simply return the value passed to {@link Function#apply apply(t)}
     *            (the inner function will never be called).
     * @return A function that will call the given function on its own result a
     *         number of times as specified by the depth parameter.
     */
    @Override
    default LongUnaryOperator recursive(int depth) {
        return t -> recurse(t, depth);
    }
    
    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static LongUnaryOperator identity() {
        return t -> t;
    }
}
