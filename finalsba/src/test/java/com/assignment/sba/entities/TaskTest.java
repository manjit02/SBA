package com.assignment.sba.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskTest extends DtoTest<Task> {
		public static final String[] SET_VALUES = new String[] { "getClass" };
		public static final Set<String> ignoreFields = new HashSet<>(Arrays.asList(SET_VALUES));
		public TaskTest() {
			super(null,ignoreFields); 
		}
		@Override
	    protected Task getInstance() {
	        return new Task();
	    }

}