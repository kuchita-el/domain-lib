package com.github.dkurata38.library.domain_lib.enumuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public class EnumSearcher<E extends Enum<E> & EnumSearchable, T> {
	private final Class<E> enumClass;
	private final Function<E, T> getter;

	public E search(@NonNull T key) {
		return Arrays.stream(enumClass.getEnumConstants())
				.filter(enumConstant -> getter.apply(enumConstant).equals(key))
				.findFirst()
				.orElseThrow();
	}
}
