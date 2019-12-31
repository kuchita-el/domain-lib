package com.github.dkurata38.library.domain_lib.persistence;

public interface Identifier<T> {
	Class<T> getType();
	String getValue();
}
