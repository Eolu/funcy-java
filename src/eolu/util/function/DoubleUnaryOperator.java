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
 * Represents an operation on a single {@code double}-valued operand that
 * produces a {@code double}-valued result. This is the primitive type
 * specialization of {@link UnaryOperator} for {@code double}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #applyAsDouble(double)}.
 *
 * @see UnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface DoubleUnaryOperator extends
                                     DoubleFunction<Double>,
                                     UnaryOperator<Double>,
                                     ToDoubleFunction<Double>,
                                     java.util.function.DoubleUnaryOperator {
    
    /**
     * @see {@link Math#abs(double)}
     */
    public static final DoubleUnaryOperator ABS = Math::abs;
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    double applyAsDouble(double value);
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default double applyAsDouble(Double value) {
        return applyAsDouble(value.doubleValue());
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Double apply(double value) {
        return applyAsDouble(value);
    }
    
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    default Double apply(Double value) {
        return applyAsDouble(value.doubleValue());
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    @Override
    default DoubleSupplier applyPartial(double t) {
        return () -> applyAsDouble(t);
    }
    
    /**
     * Consume a function.
     * 
     * @param consumer The consumer to use in consuming.
     * @return A Consumer which passes it's argument to this function and then
     *         passes the result into the given consumer.
     */
    @Override
    default DoubleConsumer consume(DoubleConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(applyAsDouble(t));
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
    default <S> DoubleFunction<S> mapToObj(DoubleFunction<? extends S> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoublePredicate mapToPredicate(DoublePredicate functor) {
        Objects.requireNonNull(functor);
        return t -> functor.test(applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleToIntFunction mapToInt(DoubleToIntFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsInt(applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleToLongFunction mapToLong(DoubleToLongFunction functor) {
        Objects.requireNonNull(functor);
        return t -> functor.applyAsLong(applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    @Override
    default DoubleUnaryOperator map(UnaryOperator<Double> functor) {
        Objects.requireNonNull(functor);
        return t -> functor.apply(applyAsDouble(t));
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
    default double recurse(double value, DoublePredicate terminalCondition) {
        while (terminalCondition.test(value))
            value = applyAsDouble(value);
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
    default double recurse(double value, DoublePredicate terminalCondition, int maxDepth) {
        for (int i = 0; i < maxDepth && terminalCondition.test(value); i++)
            value = applyAsDouble(value);
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
    default double recurse(double value, int depth) {
        for (int i = 0; i < depth; i++)
            value = applyAsDouble(value);
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
    default DoubleUnaryOperator recursive(DoublePredicate terminalCondition) {
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
    default DoubleUnaryOperator recursive(DoublePredicate terminalCondition, int maxDepth) {
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
    default DoubleUnaryOperator recursive(int depth) {
        return t -> recurse(t, depth);
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
