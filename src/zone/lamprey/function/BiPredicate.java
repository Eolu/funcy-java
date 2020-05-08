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
 * Represents a predicate (boolean-valued function) of two arguments. This is
 * the two-arity specialization of {@link Predicate}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #test(Object, Object)}.
 *
 * @param <T> the type of the first argument to the predicate
 * @param <U> the type of the second argument the predicate
 *
 * @see Predicate
 * @since 1.8
 */
@FunctionalInterface
public interface BiPredicate<T, U> extends BiFunction<T, U, Boolean>, java.util.function.BiPredicate<T, U> {
    
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    @Override
    boolean test(T t, U u);
    
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    @Override
    default Boolean apply(T t, U u) {
        return test(t, u);
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
    default BooleanSupplier applyPartial(T t, U u) {
        return () -> test(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default Predicate<U> applyPartialL(T t) {
        return u -> test(t, u);
    }
    
    /**
     * Performs a partial application, resulting in a function that calls this with
     * its argument and the argument given here.
     *
     * @param u the function argument
     * @return the function result
     */
    @Override
    default Predicate<T> applyPartialR(U u) {
        return t -> test(t, u);
    }
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical AND
     * of this predicate and another. When evaluating the composed predicate, if
     * this predicate is {@code false}, then the {@code other} predicate is not
     * evaluated.
     *
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to
     * the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting logical AND
     *         of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default BiPredicate<T, U> and(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) && other.test(t, u);
    }
    
    /**
     * Lift a function.
     * 
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    default BiPredicate<T, U> map(UnaryOperator<Boolean> functor) {
        Objects.requireNonNull(functor);
        return (t, u) -> functor.apply(test(t, u));
    }
    
    /**
     * Returns a predicate that represents the logical negation of this predicate.
     *
     * @return a predicate that represents the logical negation of this predicate
     */
    @Override
    default BiPredicate<T, U> negate() {
        return (T t, U u) -> !test(t, u);
    }
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical OR of
     * this predicate and another. When evaluating the composed predicate, if this
     * predicate is {@code true}, then the {@code other} predicate is not evaluated.
     *
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to
     * the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting logical OR
     *         of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default BiPredicate<T, U> or(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) || other.test(t, u);
    }
    
    /**
     * Returns a predicate that tests if two arguments are equal according to
     * {@link Objects#equals(Object, Object)}.
     *
     * @param <T> the first type of arguments to the predicate.
     * @param <U> the second type of arguments to the predicate.
     * @return a predicate that tests if two arguments are equal according to
     *         {@link Objects#equals(Object, Object)}
     */
    @SuppressWarnings("unlikely-arg-type")
    static <T, U> BiPredicate<T, U> isEqual() {
        return Objects::equals;
    }
    
    /**
     * Returns a predicate that is the negation of the supplied predicate. This is
     * accomplished by returning result of the calling {@code target.negate()}.
     *
     * @param <T> the type of arguments to the specified predicate
     * @param target predicate to negate
     *
     * @return a predicate that negates the results of the supplied predicate
     *
     * @throws NullPointerException if target is null
     *
     * @since 11
     */
    @SuppressWarnings("unchecked")
    static <T, U> BiPredicate<T, U> not(BiPredicate<? super T, ? super U> target) {
        Objects.requireNonNull(target);
        return (BiPredicate<T, U>) target.negate();
    }
    
    /**
     * Returns a predicate that is always true.
     *
     * @param <T> the first type of arguments to the predicate.
     * @param <U> the second type of arguments to the predicate.
     * @return a predicate that is always true.
     */
    static <T, U> BiPredicate<T, U> alwaysTrue() {
        return (t, u) -> true;
    }
    
    /**
     * Returns a predicate that is always false.
     *
     * @param <T> the first type of arguments to the predicate.
     * @param <U> the second type of arguments to the predicate.
     * @return a predicate that is always true.
     */
    static <T, U> BiPredicate<T, U> alwaysFalse() {
        return (t, u) -> false;
    }
}
