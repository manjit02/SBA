package com.assignment.sba.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ParentTaskTest extends DtoTest<ParentTask> {
		public static final String[] SET_VALUES = new String[] { "getClass" };
		public static final Set<String> ignoreFields = new HashSet<>(Arrays.asList(SET_VALUES));
		public ParentTaskTest() {
			super(null,ignoreFields); 
		}
		@Override
	    protected ParentTask getInstance() {
	        return new ParentTask();
	    }

}
