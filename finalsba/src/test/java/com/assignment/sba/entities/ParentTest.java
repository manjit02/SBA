package com.assignment.sba.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ParentTest extends DtoTest<Parent> {
		public static final String[] SET_VALUES = new String[] { "getClass" };
		public static final Set<String> ignoreFields = new HashSet<>(Arrays.asList(SET_VALUES));
		public ParentTest() {
			super(null,ignoreFields); 
		}
		@Override
	    protected Parent getInstance() {
	        return new Parent();
	    }

}