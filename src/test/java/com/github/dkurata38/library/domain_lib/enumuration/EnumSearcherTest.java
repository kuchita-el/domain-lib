package com.github.dkurata38.library.domain_lib.enumuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EnumSearcherTest {

	static EnumSearcher<TestEnum, String> searcher = new EnumSearcher<>(TestEnum.class, TestEnum::getCode);

	@ParameterizedTest
	@EnumSource(TestEnum.class)
	@DisplayName("定数値で定義されているコードを指定した場合")
	void testSearch(TestEnum constant) {
		assertEquals(constant, searcher.search(constant.getCode()));
	}

	@ParameterizedTest
	@NullSource
	@DisplayName("Nullを指定した場合")
	void testSearchByNull(String code) {
		assertThrows(NullPointerException.class, () -> searcher.search(code));
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "3", "4"})
	@DisplayName("存在しないコードを指定して失敗する場合")
	void testSearchByUndefinedCode() {
		assertThrows(NoSuchElementException.class, () -> searcher.search("3"));
	}

	@RequiredArgsConstructor
	@Getter
	static enum TestEnum implements EnumSearchable {
		TEST_1("1"),TEST_2("2");
		private final String code;
	}
}
