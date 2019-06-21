/*
 * Copyright 2018 Griffin O'Neill
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

import eolu.util.function.BiConsumer;
import eolu.util.function.BiFunction;
import eolu.util.function.BiPredicate;
import eolu.util.function.BinaryOperator;
import eolu.util.function.BooleanSupplier;
import eolu.util.function.Consumer;
import eolu.util.function.DoubleBinaryOperator;
import eolu.util.function.DoubleConsumer;
import eolu.util.function.DoubleFunction;
import eolu.util.function.DoublePredicate;
import eolu.util.function.DoubleSupplier;
import eolu.util.function.DoubleToIntFunction;
import eolu.util.function.DoubleToLongFunction;
import eolu.util.function.DoubleUnaryOperator;
import eolu.util.function.Function;
import eolu.util.function.IntBinaryOperator;
import eolu.util.function.IntConsumer;
import eolu.util.function.IntFunction;
import eolu.util.function.IntPredicate;
import eolu.util.function.IntSupplier;
import eolu.util.function.IntToDoubleFunction;
import eolu.util.function.IntToLongFunction;
import eolu.util.function.IntUnaryOperator;
import eolu.util.function.LongBinaryOperator;
import eolu.util.function.LongConsumer;
import eolu.util.function.LongFunction;
import eolu.util.function.LongPredicate;
import eolu.util.function.LongSupplier;
import eolu.util.function.LongToDoubleFunction;
import eolu.util.function.LongToIntFunction;
import eolu.util.function.LongUnaryOperator;
import eolu.util.function.ObjDoubleConsumer;
import eolu.util.function.ObjIntConsumer;
import eolu.util.function.ObjLongConsumer;
import eolu.util.function.Predicate;
import eolu.util.function.Supplier;
import eolu.util.function.TernaryOperator;
import eolu.util.function.ToDoubleBiFunction;
import eolu.util.function.ToDoubleFunction;
import eolu.util.function.ToIntBiFunction;
import eolu.util.function.ToIntFunction;
import eolu.util.function.ToLongBiFunction;
import eolu.util.function.ToLongFunction;
import eolu.util.function.UnaryOperator;

/**
 * This class contains old code or testing stuff. Keeping this around for a
 * while.
 */
public class TestingStuff {
    
    static final TernaryOperator<Double> MAGNITUDE = TestingStuff::magnitude;
    static final DoubleSupplier          RANDOM    = Math::random;
    
    public static void main(String... args) {
        // Do some testing
        
        var customRandom = RANDOM.map(val -> val * 9)
                                 .map(Math::exp)
                                 .map(val -> val - 457)
                                 .map(Math::round)
                                 .map(val -> val / 2)
                                 .map(val -> val + 10000);
        
        MAGNITUDE.applyPartialL(customRandom.getAsDouble())
                 .applyPartialR(customRandom.getAsDouble())
                 .applyPartial(customRandom.getAsDouble())
                 .map(l -> 7 * l)
                 .map(l -> l * 100)
                 .mapToDouble(l -> l * 0.001)
                 .map(Object::toString)
                 .consume(System.out::println)
                 .run();
    }
    
    // A magnitude function
    private static final double magnitude(double x, double y, double z) {
        return (long) Math.sqrt((x * x) + (y * y) + (z * z));
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> Consumer<U> applyPartial(BiConsumer<T, U> fn, T t) {
        return u -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param <R> The return type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U, R> Function<U, R> applyPartial(BiFunction<T, U, R> fn, T t) {
        return u -> fn.apply(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The param and return type.
     * @param fn The function to use in the partial application.
     * @param t The leftmost parameter.
     * @return A partially-applied function.
     */
    public static <T> UnaryOperator<T> applyPartial(BinaryOperator<T> fn, T t) {
        return u -> fn.apply(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> Predicate<U> applyPartial(BiPredicate<T, U> fn, T t) {
        return u -> fn.test(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The leftmost parameter.
     * @return A partially-applied function.
     */
    public static DoubleUnaryOperator applyPartial(DoubleBinaryOperator fn, double t) {
        return u -> fn.applyAsDouble(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <R> The return type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <R> Supplier<R> applyPartial(DoubleFunction<R> fn, double t) {
        return () -> fn.apply(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> BooleanSupplier applyPartial(DoublePredicate fn, double t) {
        return () -> fn.test(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static IntSupplier applyPartial(DoubleToIntFunction fn, double t) {
        return () -> fn.applyAsInt(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static LongSupplier applyPartial(DoubleToLongFunction fn, double t) {
        return () -> fn.applyAsLong(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> DoubleSupplier applyPartial(DoubleUnaryOperator fn, double t) {
        return () -> fn.applyAsDouble(t);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The leftmost parameter.
     * @return A partially-applied function.
     */
    public static IntUnaryOperator applyPartial(IntBinaryOperator fn, int t) {
        return u -> fn.applyAsInt(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param consumer becomes a
     * no-param runnable.
     * 
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static Runnable applyPartial(IntConsumer fn, int t) {
        return () -> fn.accept(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <R> The return type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <R> Supplier<R> applyPartial(IntFunction<R> fn, int t) {
        return () -> fn.apply(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> BooleanSupplier applyPartial(IntPredicate fn, int t) {
        return () -> fn.test(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static DoubleSupplier applyPartial(IntToDoubleFunction fn, int t) {
        return () -> fn.applyAsDouble(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static LongSupplier applyPartial(IntToLongFunction fn, int t) {
        return () -> fn.applyAsLong(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> IntSupplier applyPartial(IntUnaryOperator fn, int t) {
        return () -> fn.applyAsInt(t);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The leftmost parameter.
     * @return A partially-applied function.
     */
    public static LongUnaryOperator applyPartial(LongBinaryOperator fn, long t) {
        return u -> fn.applyAsLong(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param consumer becomes a
     * no-param runnable.
     * 
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static Runnable applyPartial(LongConsumer fn, long t) {
        return () -> fn.accept(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <R> The return type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <R> Supplier<R> applyPartial(LongFunction<R> fn, long t) {
        return () -> fn.apply(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> BooleanSupplier applyPartial(LongPredicate fn, long t) {
        return () -> fn.test(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static DoubleSupplier applyPartial(LongToDoubleFunction fn, long t) {
        return () -> fn.applyAsDouble(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static IntSupplier applyPartial(LongToIntFunction fn, long t) {
        return () -> fn.applyAsInt(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> LongSupplier applyPartial(LongUnaryOperator fn, long t) {
        return () -> fn.applyAsLong(t);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> DoubleConsumer applyPartial(ObjDoubleConsumer<T> fn, T t) {
        return u -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> IntConsumer applyPartial(ObjIntConsumer<T> fn, T t) {
        return u -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> LongConsumer applyPartial(ObjLongConsumer<T> fn, T t) {
        return u -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <T> The parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> BooleanSupplier applyPartial(Predicate<T> fn, T t) {
        return () -> fn.test(t);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToDoubleFunction<U> applyPartial(ToDoubleBiFunction<T, U> fn, T t) {
        return u -> fn.applyAsDouble(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <T> The parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> DoubleSupplier applyPartial(ToDoubleFunction<T> fn, T t) {
        return () -> fn.applyAsDouble(t);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToIntFunction<U> applyPartial(ToIntBiFunction<T, U> fn, T t) {
        return u -> fn.applyAsInt(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <T> The parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> IntSupplier applyPartial(ToIntFunction<T> fn, T t) {
        return () -> fn.applyAsInt(t);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToLongFunction<U> applyPartial(ToLongBiFunction<T, U> fn, T t) {
        return u -> fn.applyAsLong(t, u);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <T> The parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> LongSupplier applyPartial(ToLongFunction<T> fn, T t) {
        return () -> fn.applyAsLong(t);
    }
    
    /**
     * Partially apply a parameter such that a single param function becomes a
     * no-param supplier.
     * 
     * @param <T> The parameter and return type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> Supplier<T> applyPartial(UnaryOperator<T> fn, T t) {
        return () -> fn.apply(t);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> Consumer<T> applyPartialR(BiConsumer<T, U> fn, U u) {
        return t -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param <R> The return type.
     * @param fn The function to use in the partial application.
     * @param u The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U, R> Function<T, R> applyPartialR(BiFunction<T, U, R> fn, U u) {
        return t -> fn.apply(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The param and return type.
     * @param fn The function to use in the partial application.
     * @param t The rightmost parameter.
     * @return A partially-applied function.
     */
    public static <T> UnaryOperator<T> applyPartialR(BinaryOperator<T> fn, T t) {
        return u -> fn.apply(u, t);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <T> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> Predicate<T> applyPartialR(BiPredicate<T, U> fn, U u) {
        return t -> fn.test(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The rightmost parameter.
     * @return A partially-applied function.
     */
    public static DoubleUnaryOperator applyPartialR(DoubleBinaryOperator fn, double t) {
        return u -> fn.applyAsDouble(u, t);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The rightmost parameter.
     * @return A partially-applied function.
     */
    public static IntUnaryOperator applyPartialR(IntBinaryOperator fn, int t) {
        return u -> fn.applyAsInt(u, t);
    }
    
    /**
     * Partially apply a parameter such that a two param function becomes a
     * single-param function.
     * 
     * @param fn The function to use in the partial application.
     * @param t The rightmost parameter.
     * @return A partially-applied function.
     */
    public static LongUnaryOperator applyPartialR(LongBinaryOperator fn, long t) {
        return u -> fn.applyAsLong(u, t);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> Consumer<T> applyPartialR(ObjDoubleConsumer<T> fn, double u) {
        return t -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> Consumer<T> applyPartialR(ObjIntConsumer<T> fn, int u) {
        return t -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two param consumer becomes a
     * single-param consumer.
     * 
     * @param <T> The first parameter type.
     * @param fn The consumer to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T> Consumer<T> applyPartialR(ObjLongConsumer<T> fn, long u) {
        return t -> fn.accept(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToDoubleFunction<T> applyPartialR(ToDoubleBiFunction<T, U> fn, U u) {
        return t -> fn.applyAsDouble(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToIntFunction<T> applyPartialR(ToIntBiFunction<T, U> fn, U u) {
        return t -> fn.applyAsInt(t, u);
    }
    
    /**
     * Partially apply a parameter such that a two-param function becomes a
     * single-param function.
     * 
     * @param <T> The first parameter type.
     * @param <U> The second parameter type.
     * @param fn The function to use in the partial application.
     * @param t The parameter to apply.
     * @return A partially-applied function.
     */
    public static <T, U> ToLongFunction<T> applyPartialR(ToLongBiFunction<T, U> fn, U u) {
        return t -> fn.applyAsLong(t, u);
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The intermediate parameter type.
     * @param <R> The final return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> DoubleFunction<R> lift(DoubleFunction<T> fn, Function<T, R> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoublePredicate lift(DoubleFunction<T> fn, Predicate<T> functor) {
        return t -> functor.test(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleUnaryOperator lift(DoubleFunction<T> fn, ToDoubleFunction<T> functor) {
        return t -> functor.applyAsDouble(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleToIntFunction lift(DoubleFunction<T> fn, ToIntFunction<T> functor) {
        return t -> functor.applyAsInt(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleToLongFunction lift(DoubleFunction<T> fn, ToLongFunction<T> functor) {
        return t -> functor.applyAsLong(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleFunction<T> lift(DoubleFunction<T> fn, UnaryOperator<T> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleFunction<T> lift(DoublePredicate fn, Function<Boolean, T> functor) {
        return t -> functor.apply(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoublePredicate lift(DoublePredicate fn, Predicate<Boolean> functor) {
        return t -> functor.test(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleUnaryOperator lift(DoublePredicate fn, ToDoubleFunction<Boolean> functor) {
        return t -> functor.applyAsDouble(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToIntFunction lift(DoublePredicate fn, ToIntFunction<Boolean> functor) {
        return t -> functor.applyAsInt(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToLongFunction lift(DoublePredicate fn, ToLongFunction<Boolean> functor) {
        return t -> functor.applyAsLong(fn.test(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleFunction<T> lift(DoubleToIntFunction fn, IntFunction<T> functor) {
        return t -> functor.apply(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoublePredicate lift(DoubleToIntFunction fn, IntPredicate functor) {
        return t -> functor.test(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleUnaryOperator lift(DoubleToIntFunction fn, IntToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToLongFunction lift(DoubleToIntFunction fn, IntToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToIntFunction lift(DoubleToIntFunction fn, IntUnaryOperator functor) {
        return t -> functor.applyAsInt(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> DoubleFunction<T> lift(DoubleToLongFunction fn, LongFunction<T> functor) {
        return t -> functor.apply(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoublePredicate lift(DoubleToLongFunction fn, LongPredicate functor) {
        return t -> functor.test(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleUnaryOperator lift(DoubleToLongFunction fn, LongToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToIntFunction lift(DoubleToLongFunction fn, LongToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static DoubleToLongFunction lift(DoubleToLongFunction fn, LongUnaryOperator functor) {
        return t -> functor.applyAsLong(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The intermediate parameter type.
     * @param <R> The final return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> IntFunction<R> lift(IntFunction<T> fn, Function<T, R> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntPredicate lift(IntFunction<T> fn, Predicate<T> functor) {
        return t -> functor.test(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntToDoubleFunction lift(IntFunction<T> fn, ToDoubleFunction<T> functor) {
        return t -> functor.applyAsDouble(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntUnaryOperator lift(IntFunction<T> fn, ToIntFunction<T> functor) {
        return t -> functor.applyAsInt(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntToLongFunction lift(IntFunction<T> fn, ToLongFunction<T> functor) {
        return t -> functor.applyAsLong(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntFunction<T> lift(IntFunction<T> fn, UnaryOperator<T> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntFunction<T> lift(IntPredicate fn, Function<Boolean, T> functor) {
        return t -> functor.apply(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntPredicate lift(IntPredicate fn, Predicate<Boolean> functor) {
        return t -> functor.test(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToDoubleFunction lift(IntPredicate fn, ToDoubleFunction<Boolean> functor) {
        return t -> functor.applyAsDouble(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntUnaryOperator lift(IntPredicate fn, ToIntFunction<Boolean> functor) {
        return t -> functor.applyAsInt(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToLongFunction lift(IntPredicate fn, ToLongFunction<Boolean> functor) {
        return t -> functor.applyAsLong(fn.test(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntFunction<T> lift(IntToDoubleFunction fn, DoubleFunction<T> functor) {
        return t -> functor.apply(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntPredicate lift(IntToDoubleFunction fn, DoublePredicate functor) {
        return t -> functor.test(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntUnaryOperator lift(IntToDoubleFunction fn, DoubleToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToLongFunction lift(IntToDoubleFunction fn, DoubleToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToDoubleFunction lift(IntToDoubleFunction fn, DoubleUnaryOperator functor) {
        return t -> functor.applyAsDouble(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntFunction<T> lift(IntToLongFunction fn, LongFunction<T> functor) {
        return t -> functor.apply(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntPredicate lift(IntToLongFunction fn, LongPredicate functor) {
        return t -> functor.test(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToDoubleFunction lift(IntToLongFunction fn, LongToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntUnaryOperator lift(IntToLongFunction fn, LongToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToLongFunction lift(IntToLongFunction fn, LongUnaryOperator functor) {
        return t -> functor.applyAsLong(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> IntFunction<T> lift(IntUnaryOperator fn, IntFunction<T> functor) {
        return t -> functor.apply(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntPredicate lift(IntUnaryOperator fn, IntPredicate functor) {
        return t -> functor.test(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToDoubleFunction lift(IntUnaryOperator fn, IntToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntToLongFunction lift(IntUnaryOperator fn, IntToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static IntUnaryOperator lift(IntUnaryOperator fn, IntUnaryOperator functor) {
        return t -> functor.applyAsInt(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The intermediate parameter type.
     * @param <R> The final return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> LongFunction<R> lift(LongFunction<T> fn, Function<T, R> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongPredicate lift(LongFunction<T> fn, Predicate<T> functor) {
        return t -> functor.test(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongToDoubleFunction lift(LongFunction<T> fn, ToDoubleFunction<T> functor) {
        return t -> functor.applyAsDouble(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongToIntFunction lift(LongFunction<T> fn, ToIntFunction<T> functor) {
        return t -> functor.applyAsInt(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongUnaryOperator lift(LongFunction<T> fn, ToLongFunction<T> functor) {
        return t -> functor.applyAsLong(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The intermediate parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongFunction<T> lift(LongFunction<T> fn, UnaryOperator<T> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongFunction<T> lift(LongPredicate fn, Function<Boolean, T> functor) {
        return t -> functor.apply(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongPredicate lift(LongPredicate fn, Predicate<Boolean> functor) {
        return t -> functor.test(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToDoubleFunction lift(LongPredicate fn, ToDoubleFunction<Boolean> functor) {
        return t -> functor.applyAsDouble(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToIntFunction lift(LongPredicate fn, ToIntFunction<Boolean> functor) {
        return t -> functor.applyAsInt(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongUnaryOperator lift(LongPredicate fn, ToLongFunction<Boolean> functor) {
        return t -> functor.applyAsLong(fn.test(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongFunction<T> lift(LongToDoubleFunction fn, DoubleFunction<T> functor) {
        return t -> functor.apply(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongPredicate lift(LongToDoubleFunction fn, DoublePredicate functor) {
        return t -> functor.test(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToIntFunction lift(LongToDoubleFunction fn, DoubleToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongUnaryOperator lift(LongToDoubleFunction fn, DoubleToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToDoubleFunction lift(LongToDoubleFunction fn, DoubleUnaryOperator functor) {
        return t -> functor.applyAsDouble(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongFunction<T> lift(LongToIntFunction fn, IntFunction<T> functor) {
        return t -> functor.apply(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongPredicate lift(LongToIntFunction fn, IntPredicate functor) {
        return t -> functor.test(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToDoubleFunction lift(LongToIntFunction fn, IntToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongUnaryOperator lift(LongToIntFunction fn, IntToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToIntFunction lift(LongToIntFunction fn, IntUnaryOperator functor) {
        return t -> functor.applyAsInt(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> LongFunction<T> lift(LongUnaryOperator fn, LongFunction<T> functor) {
        return t -> functor.apply(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongPredicate lift(LongUnaryOperator fn, LongPredicate functor) {
        return t -> functor.test(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToDoubleFunction lift(LongUnaryOperator fn, LongToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongToIntFunction lift(LongUnaryOperator fn, LongToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * 
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static LongUnaryOperator lift(LongUnaryOperator fn, LongUnaryOperator functor) {
        return t -> functor.applyAsLong(fn.applyAsLong(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> Function<T, R> lift(Predicate<T> fn, Function<Boolean, R> functor) {
        return t -> functor.apply(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> Predicate<T> lift(Predicate<T> fn, Predicate<Boolean> functor) {
        return t -> functor.test(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToDoubleFunction<T> lift(Predicate<T> fn, ToDoubleFunction<Boolean> functor) {
        return t -> functor.applyAsDouble(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToIntFunction<T> lift(Predicate<T> fn, ToIntFunction<Boolean> functor) {
        return t -> functor.applyAsInt(fn.test(t));
    }
    
    /**
     * Lift a predicate.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToLongFunction<T> lift(Predicate<T> fn, ToLongFunction<Boolean> functor) {
        return t -> functor.applyAsLong(fn.test(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> Function<T, R> lift(ToDoubleFunction<T> fn, DoubleFunction<R> functor) {
        return t -> functor.apply(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> Predicate<T> lift(ToDoubleFunction<T> fn, DoublePredicate functor) {
        return t -> functor.test(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToIntFunction<T> lift(ToDoubleFunction<T> fn, DoubleToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToLongFunction<T> lift(ToDoubleFunction<T> fn, DoubleToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToDoubleFunction<T> lift(ToDoubleFunction<T> fn, DoubleUnaryOperator functor) {
        return t -> functor.applyAsDouble(fn.applyAsDouble(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> Function<T, R> lift(ToIntFunction<T> fn, IntFunction<R> functor) {
        return t -> functor.apply(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> Predicate<T> lift(ToIntFunction<T> fn, IntPredicate functor) {
        return t -> functor.test(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToDoubleFunction<T> lift(ToIntFunction<T> fn, IntToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToLongFunction<T> lift(ToIntFunction<T> fn, IntToLongFunction functor) {
        return t -> functor.applyAsLong(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToIntFunction<T> lift(ToIntFunction<T> fn, IntUnaryOperator functor) {
        return t -> functor.applyAsInt(fn.applyAsInt(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> Function<T, R> lift(ToLongFunction<T> fn, LongFunction<R> functor) {
        return t -> functor.apply(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> Predicate<T> lift(ToLongFunction<T> fn, LongPredicate functor) {
        return t -> functor.test(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToDoubleFunction<T> lift(ToLongFunction<T> fn, LongToDoubleFunction functor) {
        return t -> functor.applyAsDouble(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToIntFunction<T> lift(ToLongFunction<T> fn, LongToIntFunction functor) {
        return t -> functor.applyAsInt(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToLongFunction<T> lift(ToLongFunction<T> fn, LongUnaryOperator functor) {
        return t -> functor.applyAsLong(fn.applyAsLong(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T, R> Function<T, R> lift(UnaryOperator<T> fn, Function<T, R> functor) {
        return t -> functor.apply(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> Predicate<T> lift(UnaryOperator<T> fn, Predicate<T> functor) {
        return t -> functor.test(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToDoubleFunction<T> lift(UnaryOperator<T> fn, ToDoubleFunction<T> functor) {
        return t -> functor.applyAsDouble(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToIntFunction<T> lift(UnaryOperator<T> fn, ToIntFunction<T> functor) {
        return t -> functor.applyAsInt(fn.apply(t));
    }
    
    /**
     * Lift a function.
     *
     * @param <T> The initial parameter type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> ToLongFunction<T> lift(UnaryOperator<T> fn, ToLongFunction<T> functor) {
        return t -> functor.applyAsLong(fn.apply(t));
    }
    
    /**
     * Lift a function.
     * 
     * @param <T> The initial parameter type.
     * @param <R> The return type.
     * @param fn The function to lift.
     * @param functor The function to use in lifting.
     * @return A function that passes the result of fn through a functor to produce
     *         a lifted function.
     */
    public static <T> UnaryOperator<T> lift(UnaryOperator<T> fn, UnaryOperator<T> functor) {
        return t -> functor.apply(fn.apply(t));
    }
}