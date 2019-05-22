package com.assignment.sba.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserTest extends DtoTest<User> {
		public static final String[] SET_VALUES = new String[] { "getClass" };
		public static final Set<String> ignoreFields = new HashSet<>(Arrays.asList(SET_VALUES));
		public UserTest() {
			super(null,ignoreFields); 
		}
		@Override
	    protected User getInstance() {
	        return new User();
	    }

}