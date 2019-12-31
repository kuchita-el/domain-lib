package com.github.dkurata38.library.domain_lib.date_time.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeUtil {
	public static boolean overlaps(LocalDateTime start1, LocalDateTime end1,
							LocalDateTime start2, LocalDateTime end2) {
		assert start1.isBefore(end1);
		assert start2.isBefore(end2);

		if (!start2.isAfter(start1) && end2.isAfter(start1)) {
			return true;
		}

		if (start2.isBefore(end1) && !end2.isBefore(end1)) {
			return true;
		}

		if (!start2.isBefore(start1) && !end1.isBefore(end2)) {
			return true;
		}

		return false;
	}

	public static boolean overlaps(LocalDate start1, LocalDate end1,
							LocalDate start2, LocalDate end2) {
		return overlaps(start1.atStartOfDay(), end1.atStartOfDay(),
				start2.atStartOfDay(), end2.atStartOfDay());
	}

	public static boolean overlaps(LocalTime start1, LocalTime end1,
								   LocalTime start2, LocalTime end2) {
		LocalDate localDate = LocalDate.now();
		return overlaps(start1.atDate(localDate), end1.atDate(localDate),
				start2.atDate(localDate), end2.atDate(localDate));
	}
}
