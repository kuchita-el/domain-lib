package com.github.dkurata38.library.domain_lib.collection;

import lombok.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

	@ParameterizedTest
	@EmptySource
	@MethodSource(value = {"entitiesProvider"})
	void testHead(List<Entity> entities) {
		if (entities.isEmpty()) {
			assertTrue(CollectionUtils.head(entities).isEmpty());
		} else {
			assertEquals(Optional.of(ENTITIES[0]), CollectionUtils.head(List.of(ENTITIES)));
		}
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource(value = {"entitiesProvider"})
	void allMatch(List<Entity> entities) {
		Predicate<Entity> truePredicate = entity -> !entity.getValue().isEmpty();
		Predicate<Entity> falsePredicate = entity -> !entity.getValue().equals("1");
		if (entities.isEmpty()) {
			assertTrue(CollectionUtils.allMatch(entities,
					truePredicate));
			assertTrue(CollectionUtils.allMatch(entities,
					falsePredicate));
		} else {
			assertTrue(CollectionUtils.allMatch(entities,
					truePredicate));
			assertFalse(CollectionUtils.allMatch(entities,
					falsePredicate));
		}
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource(value = {"entitiesProvider"})
	void anyMatch(List<Entity> entities) {
		Predicate<Entity> truePredicate = entity -> entity.getValue().equals("1");
		Predicate<Entity> falsePredicate = entity -> entity.getValue().equals("7");
		if (entities.isEmpty()) {
			assertFalse(CollectionUtils.anyMatch(entities,
					truePredicate));
			assertFalse(CollectionUtils.anyMatch(entities,
					falsePredicate));
		} else {
			assertTrue(CollectionUtils.anyMatch(entities,
					truePredicate));
			assertFalse(CollectionUtils.anyMatch(entities,
					falsePredicate));
		}
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource(value = {"entitiesProvider"})
	void noneMatch(List<Entity> entities) {
		Predicate<Entity> truePredicate = entity -> entity.getValue().equals("1");
		Predicate<Entity> falsePredicate = entity -> entity.getValue().equals("7");
		if (entities.isEmpty()) {
			assertTrue(CollectionUtils.noneMatch(entities,
					truePredicate));
			assertTrue(CollectionUtils.noneMatch(entities,
					falsePredicate));
		} else {
			assertFalse(CollectionUtils.noneMatch(entities,
					truePredicate));
			assertTrue(CollectionUtils.noneMatch(entities,
					falsePredicate));
		}
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource(value = {"entitiesProvider"})
	void findAll(List<Entity> entities) {
		Predicate<Entity> truePredicate = entity -> !entity.getValue().equals("1");
		List<Entity> expected = entities
				.stream()
				.filter(truePredicate)
				.collect(Collectors.toList());
		assertEquals(expected, CollectionUtils.findAll(entities, truePredicate));
	}

	@Test
	void findAny() {
	}

	@Test
	void findFirst() {
	}

	@Test
	void sum() {
	}

	@Test
	void maxBy() {
	}

	@Test
	void testMaxBy() {
	}

	@Test
	void minBy() {
	}

	@Test
	void testMinBy() {
	}

	@Test
	void map() {
	}

	@Value
	static class Key {
		private final Integer value;
	}

	@Value
	static class Entity {
		private final Key key;
		private final String value;
	}

	@Value
	static class GroupByKey {
		private final Key key;
		private final Collection<Entity> entities;
	}

	@Value
	static class GroupByValue {
		private final String value;
		private final Collection<Entity> entities;
	}

	static final Entity[] ENTITIES = new Entity[]{
			new Entity(new Key(1), "1"),
			new Entity(new Key(2), "1"),
			new Entity(new Key(3), "2"),
			new Entity(new Key(4), "2"),
			new Entity(new Key(5), "2"),
			new Entity(new Key(6), "3")
	};
	static Stream<Arguments> entitiesProvider() {
		return Stream.of(Arguments.arguments(List.of(ENTITIES)));
	}

	static final Key[] KEYS = Arrays.stream(ENTITIES)
			.map(Entity::getKey)
			.toArray(Key[]::new);
	static Stream<Arguments> keyProvider() {
		return Stream.of(KEYS)
				.map(Arguments::arguments);
	}

	static final String[] VALUES = Arrays.stream(ENTITIES)
			.map(Entity::getValue)
			.distinct()
			.toArray(String[]::new);
	static Stream<Arguments> valueProvider() {
		return Stream.of(VALUES)
				.map(Arguments::arguments);
	}

	@ParameterizedTest
	@MethodSource(value = {"keyProvider"})
	void testGroupingBy1Key(Key key) {

		Map<Key, Collection<Entity>> mapByKey =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getKey, Function.identity());

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getKey().equals(key))
				.collect(Collectors.toList());
		assertEquals(expectedEntities.size(), mapByKey.get(key).size());
		assertEquals(expectedEntities, mapByKey.get(key));
	}

	@ParameterizedTest
	@MethodSource(value = {"valueProvider"})
	void testGroupingBy1Value(String value) {

		Map<String, Collection<Entity>> mapByValue =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getValue, Function.identity());

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getValue().equals(value))
				.collect(Collectors.toList());
		assertEquals(expectedEntities.size(), mapByValue.get(value).size());
		assertEquals(expectedEntities, mapByValue.get(value));
	}

	@ParameterizedTest
	@MethodSource(value = {"keyProvider"})
	void testGroupingBy2Key(Key key) {
		Collection<GroupByKey> groupByKeys =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getKey, Function.identity(), GroupByKey::new);

		GroupByKey groupByKey = groupByKeys.stream()
				.filter(value -> value.getKey().equals(key))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByKey);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getKey().equals(key))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByKey.getEntities());
	}

	@ParameterizedTest
	@MethodSource(value = {"valueProvider"})
	void testGroupingBy2Value(String value) {
		Collection<GroupByValue> groupByKeys =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getValue, Function.identity(), GroupByValue::new);

		GroupByValue groupByValue = groupByKeys.stream()
				.filter(e -> e.getValue().equals(value))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByValue);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getValue().equals(value))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByValue.getEntities());
	}

	@ParameterizedTest
	@MethodSource(value = {"keyProvider"})
	void testGroupingBy3Key(Key key) {
		Collection<GroupByKey> groupByKeys =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getKey, GroupByKey::new);

		GroupByKey groupByKey = groupByKeys.stream()
				.filter(value -> value.getKey().equals(key))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByKey);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getKey().equals(key))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByKey.getEntities());
	}

	@ParameterizedTest
	@MethodSource(value = {"valueProvider"})
	void testGroupingBy3Value(String value) {
		Collection<GroupByValue> groupByValues =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getValue, GroupByValue::new);

		GroupByValue groupByValue = groupByValues.stream()
				.filter(e -> e.getValue().equals(value))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByValue);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getValue().equals(value))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByValue.getEntities());
	}

	@ParameterizedTest
	@MethodSource(value = {"keyProvider"})
	void testGroupingBy4Key(Key key) {
		Collection<GroupByKey> groupByKeys =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getKey, GroupByKey::new);

		GroupByKey groupByKey = groupByKeys.stream()
				.filter(value -> value.getKey().equals(key))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByKey);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getKey().equals(key))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByKey.getEntities());
	}

	@ParameterizedTest
	@MethodSource(value = {"valueProvider"})
	void testGroupingBy4Value(String value) {
		Collection<GroupByValue> groupByKeys =
				CollectionUtils.groupingBy(List.of(ENTITIES), Entity::getValue, GroupByValue::new);

		GroupByValue groupByValue = groupByKeys.stream()
				.filter(e -> e.getValue().equals(value))
				.findFirst()
				.orElse(null);

		assertNotNull(groupByValue);

		List<Entity> expectedEntities = Arrays.stream(ENTITIES)
				.filter(entity -> entity.getValue().equals(value))
				.collect(Collectors.toList());

		assertEquals(expectedEntities, groupByValue.getEntities());
	}

	@Test
	void toMap() {
	}

	@Test
	void testToMap() {
	}

	@Test
	void collect() {
	}
}
