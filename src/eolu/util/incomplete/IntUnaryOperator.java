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
package eolu.util.incomplete;

import java.util.Objects;

import eolu.util.function.Function;
import eolu.util.function.IntFunction;
import eolu.util.function.ToIntFunction;
import eolu.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code int}-valued operand that produces
 * an {@code int}-valued result. This is the primitive type specialization of
 * {@link UnaryOperator} for {@code int}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsInt(int)}.
 *
 * @see UnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface IntUnaryOperator extends IntFunction<Integer>, UnaryOperator<Integer>, ToIntFunction<Integer> {
    
    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    int applyAsInt(int operand);
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default int applyAsInt(Integer value) {
        return applyAsInt(value.intValue());
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Integer apply(int value) {
        return applyAsInt(value);
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Integer apply(Integer value) {
        return applyAsInt(value.intValue());
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    @Override
    default IntSupplier applyPartial(int t) {
        return () -> applyAsInt(t);
    }
    
    /**
     * Returns a composed operator that first applies the {@code before} operator to
     * its input, and then applies this operator to the result. If evaluation of
     * either operator throws an exception, it is relayed to the caller of the
     * composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before} operator
     *         and then applies this operator
     * @throws NullPointerException if before is null
     *
     * @see #andThen(IntUnaryOperator)
     */
    default IntUnaryOperator compose(IntUnaryOperator before) {
        Objects.requireNonNull(before);
        return (int v) -> applyAsInt(before.applyAsInt(v));
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
    default <S> IntFunction<S> map(IntFunction<? extends S> functor) {
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
    @Override
    default IntPredicate map(IntPredicate functor) {
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
    @Override
    default IntToDoubleFunction map(IntToDoubleFunction functor) {
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
    @Override
    default IntToLongFunction map(IntToLongFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsLong(applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default IntUnaryOperator map(UnaryOperator<Integer> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsInt(t));
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
    default int recurse(int value, IntPredicate terminalCondition) {
        while (terminalCondition.test(value))
            value = applyAsInt(value);
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
    default int recurse(int value, IntPredicate terminalCondition, int maxDepth) {
        for (int i = 0; i < maxDepth && terminalCondition.test(value); i++)
            value = applyAsInt(value);
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
    default int recurse(int value, int depth) {
        for (int i = 0; i < depth; i++)
            value = applyAsInt(value);
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
    default IntUnaryOperator recursive(IntPredicate terminalCondition) {
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
    default IntUnaryOperator recursive(IntPredicate terminalCondition, int maxDepth) {
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
    default IntUnaryOperator recursive(int depth) {
        return t -> recurse(t, depth);
    }
    
    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static IntUnaryOperator identity() {
        return t -> t;
    }
}
