package com.github.dkurata38.library.domain_lib.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public final class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * Get the first element of collection.
	 * @param collection Source
	 * @param <T> Type of collection's element
	 * @return {@link Optional#empty} if collection is empty.
	 */
	public static <T> Optional<T> head(Collection<T> collection) {
		return collection.isEmpty() ? Optional.empty() : Optional.of(collection.iterator().next());
	}

	/**
	 * Check whether all collection's elements satisfy predicate.
	 * Return always true if source collection is empty.
	 * See also {@link java.util.stream.Stream#allMatch(Predicate)}.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return true if all collection's element satisfy predicate or collection is empty.
	 */
	public static <T> boolean allMatch(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.allMatch(predicate);
	}

	/**
	 * Check whether at least one collection's element satisfies predicate.
	 * Return always false if source collection is empty.
	 * See also {@link java.util.stream.Stream#anyMatch(Predicate)}.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return true if there is at least one element satisfying predicate in collection.
	 */
	public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.anyMatch(predicate);
	}

	/**
	 * Check whether none of collection's elements satisfy predicate.
	 * Return always true if source collection is empty.
	 * See also {@link java.util.stream.Stream#noneMatch(Predicate)}.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return true if none of collection's element satisfy predicate or collection is empty.
	 */
	public static <T> boolean noneMatch(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.noneMatch(predicate);
	}

	/**
	 * Find all elements satisfying predicate.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return Collection of elements satisfying predicate.
	 */
	public static <T> Collection<T> findAll(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.filter(predicate)
				.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Find one element satisfying predicate regardless of collection's order.
	 * Use {@link CollectionUtils#findFirst(Collection, Predicate)} when you want to get the first element of satisfying elements.
	 * Return {@link Optional#empty()} if collection is empty of none of elements satisfy predicate.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return {@link Optional#empty()} if none of collection's element satisfy predicate or collection is empty.
	 */
	public static <T> Optional<T> findAny(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.filter(predicate)
				.findAny();
	}

	/**
	 * Find first element satisfying predicate.
	 * Return {@link Optional#empty()} if collection is empty of none of elements satisfy predicate.
	 * @param collection Source
	 * @param predicate Predicate
	 * @param <T> Type of collection's element
	 * @return {@link Optional#empty()} if none of collection's element satisfy predicate or collection is empty.
	 */
	public static <T> Optional<T> findFirst(Collection<T> collection, Predicate<T> predicate) {
		return collection
				.stream()
				.filter(predicate)
				.findFirst();
	}

	/**
	 * Sum {@link Integer} value generated from collection elements.
	 * Generating strategy is defined as {@link ToIntFunction}
	 * @param collection Source
	 * @param function Strategy of generate {@link Integer} value from collection element.
	 * @param <T> Type of collection's element.
	 * @return {@code 0} if collection is empty.
	 */
	public static <T> int sum(Collection<T> collection, ToIntFunction<T> function) {
		return collection
				.stream()
				.mapToInt(function)
				.sum();
	}

	/**
	 * Get maximum element of collection.
	 * Comparison strategy is defined as {@link Comparator}
	 * Return {@link Optional#empty()} if collection is empty.
	 * @param collection Source
	 * @param comparator Strategy of comparing collection elements.
	 * @param <T> Type of collection's element.
	 * @return {@link Optional#empty()} if collection is empty.
	 */
	public static <T> Optional<T> maxBy(Collection<T> collection, Comparator<T> comparator) {
		return collection
				.stream()
				.max(comparator);
	}

	/**
	 * Get maximum element of collection.
	 * In this method, collection's elements is compared by comparable value generated by function.
	 * Return {@link Optional#empty()} if collection is empty.
	 * @param collection Source
	 * @param function Strategy of comparing collection elements.
	 * @param <T> Type of collection's element.
	 * @return {@link Optional#empty()} if collection is empty.
	 */
	public static <T, S extends Comparable<S>> Optional<T> maxBy(Collection<T> collection, Function<T, S> function) {
		return maxBy(collection, Comparator.comparing(function));
	}

	/**
	 * Get minimum element of collection.
	 * Comparison strategy is defined as {@link Comparator}
	 * Return {@link Optional#empty()} if collection is empty.
	 * @param collection Source
	 * @param comparator Strategy of comparing collection elements.
	 * @param <T> Type of collection's element.
	 * @return {@link Optional#empty()} if collection is empty.
	 */
	public static <T> Optional<T> minBy(Collection<T> collection, Comparator<T> comparator) {
		return collection
				.stream()
				.min(comparator);
	}

	/**
	 * Get minimum element of collection.
	 * In this method, collection's elements is compared by comparable value generated by function.
	 * Return {@link Optional#empty()} if collection is empty.
	 * @param collection Source
	 * @param function Strategy of comparing collection elements.
	 * @param <T> Type of collection's element.
	 * @return {@link Optional#empty()} if collection is empty.
	 */
	public static <T, S extends Comparable<S>> Optional<T> minBy(Collection<T> collection, Function<T, S> function) {
		return minBy(collection, Comparator.comparing(function));
	}

	/**
	 * Generate other collection from parameter's collection.
	 * Each parameter's collection elements is transferred by function.
	 * @param collection Source
	 * @param function Strategy of transferring source collection's element to result collection's element.
	 * @param <T> Type of source collection's element.
	 * @param <S> Type of result collection's element.
	 * @return Transferred collection
	 */
	public static <T, S> Collection<S> map(Collection<T> collection, Function<T, S> function){
		return collection.stream()
				.map(function)
				.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Collect collection as key-value {@link Map}.
	 * Prior latter element if element with same key exists.
	 * @param collection Source
	 * @param keyExtractor Function generating the key of map from collection element.
	 * @param valueExtractor Function generating the value of map from collection element.
	 * @param <S> Type of the collection element.
	 * @param <K> Type of map key.
	 * @param <V> Type of map value.
	 * @return map
	 */
	public static <S, K extends Comparable<K>, V> Map<K, V> toMap(Collection<S> collection, Function<S, K> keyExtractor, Function<S, V> valueExtractor) {
		return collection.stream()
				.collect(Collectors.toUnmodifiableMap(keyExtractor, valueExtractor, (v, v2) -> v2));
	}

	/**
	 * Collect collection as key-value {@link Map} that has collection elements of value.
	 * Prior latter element if element with same key exists.
	 * @param collection Source
	 * @param keyExtractor Function generating the key of map from collection element.
	 * @param <V> Type of the collection element and map of value.
	 * @param <K> Type of map value.
	 * @return map
	 */
	public static <K extends Comparable<K>, V> Map<K, V> toMap(Collection<V> collection, Function<V, K> keyExtractor) {
		return toMap(collection, keyExtractor, Function.identity());
	}

	/**
	 * Grouping collection by some key, and then return key-values map.
	 * @param collection Grouping target
	 * @param keyExtractor Function mapping collection element to key.
	 * @param valueExtractor Function mapping collection element to value.
	 * @param <S> Type of grouping target collection element.
	 * @param <K> Type of map's key.
	 * @param <V> Type of map's value.
	 * @return Grouping result.
	 */
	public static <S, K, V> Map<K, Collection<V>> groupingBy(Collection<S> collection, Function<S, K> keyExtractor, Function<S, V> valueExtractor) {
		return collection.stream()
				.collect(Collectors.groupingBy(keyExtractor, Collectors.mapping(valueExtractor, Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList))));
	}

	/**
	 * Grouping collection by some key, and then return key-values as collection element.
	 * @param collection Grouping target
	 * @param keyExtractor Function mapping collection element to key.
	 * @param valueExtractor Function mapping collection element to value.
	 * @param resultFactory Function mapping key-values of map to result element.
	 * @param <S> Type of grouping target collection element.
	 * @param <K> Type of map's key.
	 * @param <V> Type of map's value.
	 * @param <R> Type of result collection element
	 * @return Grouping result.
	 */
	public static <S, K, V, R> Collection<R> groupingBy(Collection<S> collection, Function<S, K> keyExtractor, Function<S, V> valueExtractor, BiFunction<K, Collection<V>, R> resultFactory) {
		return groupingBy(collection, keyExtractor, valueExtractor)
				.entrySet()
				.stream()
				.map(entry -> resultFactory.apply(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	/**
	 * Grouping collection by some key, and then return key-values map.
	 * @param collection Grouping target
	 * @param keyExtractor Function mapping collection element to key.
	 * @param <K> Type of map's key.
	 * @param <V> Type of grouping target collection element and map's value.
	 * @return Grouping result.
	 */
	public static <K extends Comparable<K>, V> Map<K, Collection<V>> groupingBy(Collection<V> collection, Function<V, K> keyExtractor) {
		return groupingBy(collection, keyExtractor, Function.identity());
	}

	/**
	 * Grouping collection by some key, and then return key-values map.
	 * @param collection Grouping target
	 * @param keyExtractor Function mapping collection element to key.
	 * @param resultFactory Function mapping key-values of map to result element.
	 * @param <K> Type of map's key.
	 * @param <V> Type of grouping target collection element and map's value.
	 * @param <R> Type of result collection element.
	 * @return Grouping result.
	 */
	public static <K, V, R> Collection<R> groupingBy(Collection<V> collection, Function<V, K> keyExtractor, BiFunction<K, Collection<V>, R> resultFactory) {
		return groupingBy(collection, keyExtractor, Function.identity())
				.entrySet()
				.stream()
				.map(entry -> resultFactory.apply(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}
}
