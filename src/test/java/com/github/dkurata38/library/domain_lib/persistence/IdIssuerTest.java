package com.github.dkurata38.library.domain_lib.persistence;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IdIssuerTest {

	@Test
	@DisplayName("発行されたIDがコンストラクタのクラスと同一か、値がUUIDかどうか")
	void testIssue() {
		IdIssuer<TestId> idIssuer = new IdIssuer<>(TestId::new);
		TestId testId = idIssuer.issue();
		assertNotNull(testId);
		assertEquals(TestId.class, testId.getType());
		assertDoesNotThrow(() -> UUID.fromString(testId.getValue()));
	}

	@RequiredArgsConstructor
	static class TestId implements Identifier<TestId> {

		private final String code;

		@Override
		public Class<TestId> getType() {
			return TestId.class;
		}

		@Override
		public String getValue() {
			return code;
		}
	}
}
