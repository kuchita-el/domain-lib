package com.github.dkurata38.library.domain_lib.util;

import com.github.dkurata38.library.domain_lib.date_time.util.LocalDateTimeUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeUtilTest {


	@ParameterizedTest
	@CsvSource({
			"2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-09-30T23:59:59, 2019-10-01T00:00:00, false"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-09-30T23:59:59, 2019-10-01T00:00:01, true"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-10-01T00:00:00, 2019-10-01T00:00:01, true"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-10-01T23:59:59, 2019-10-02T00:00:00, true"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-10-02T00:00:00, 2019-10-02T00:00:01, false"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-10-01T00:00:00, 2019-10-02T00:00:00, true"
			, "2019-10-01T00:00:00, 2019-10-02T00:00:00, 2019-10-01T00:00:01, 2019-10-01T23:59:59, true"
	})
	void overlaps(LocalDateTime start1, LocalDateTime end1,
				  LocalDateTime start2, LocalDateTime end2, Boolean result) {
		assertEquals(result, LocalDateTimeUtil.overlaps(start1, end1, start2, end2));
	}
}
