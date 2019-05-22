package com.assignment.sba.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProjectTest extends DtoTest<Project> {
		public static final String[] SET_VALUES = new String[] { "getClass" };
		public static final Set<String> ignoreFields = new HashSet<>(Arrays.asList(SET_VALUES));
		public ProjectTest() {
			super(null,ignoreFields); 
		}
		@Override
	    protected Project getInstance() {
	        return new Project();
	    }

}