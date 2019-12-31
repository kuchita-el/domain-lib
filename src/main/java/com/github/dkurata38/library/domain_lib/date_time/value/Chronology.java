package com.github.dkurata38.library.domain_lib.date_time.value;

import lombok.NonNull;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public interface Chronology<T extends Chronology<T, V>, V extends Temporal & TemporalAdjuster> extends Comparable<T> {

	V getValue();

	boolean isAfter(@NonNull T other);

	boolean isAfterOrEqualsTo(@NonNull T other);

	boolean isBefore(@NonNull T other);

	boolean isBeforeOrEqualsTo(@NonNull T other);

	String format(@NonNull DateTimeFormatter formatter);

	boolean equals(final Object o);

	int hashCode();
}
