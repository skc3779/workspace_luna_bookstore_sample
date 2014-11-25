package com.bookstore.entities;

public enum SearchLevel {

	SIMPLE(0), // 간단
	MEDIUM(1), // 중간
	DETAIL(2); // 상세
	
    private final int value;
    
    private SearchLevel(int value) {
    	this.value = value;    	
    }
    
    public int intValue() {
    	return this.value;
    }
    
    public static SearchLevel valueOf(int value) {
    	switch(value) {
    	case 0 : return SIMPLE;
    	case 1 : return MEDIUM;
    	case 2 : return DETAIL;
    	default : 
    		throw new IllegalArgumentException();
    	}
    }
		
}
