package org.openflexo.ta.obp2.model.test;

import java.util.HashMap;
import java.util.Map;

import plug.core.IConfiguration;
import plug.explorer.buchi.nested_dfs.Color;

public class TestConfiguration implements IConfiguration<TestConfiguration> {

	private int i = 0;

	public TestConfiguration(int i) {
		super();
		this.i = i;
		System.out.println("**** New configuration " + i);
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return "Configuration(" + getI() + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestConfiguration other = (TestConfiguration) obj;
		if (i != other.i)
			return false;
		return true;
	}

	private static Map<Integer, TestConfiguration> configs = new HashMap<>();

	public static TestConfiguration getConfiguration(int i) {
		TestConfiguration returned = configs.get(i);
		if (returned == null) {
			returned = new TestConfiguration(i);
			configs.put(i, returned);
		}
		return returned;
	}

	@Override
	public TestConfiguration createCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	private Color metadata = Color.WHITE;

	@Override
	public Object getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(Object md) {
		metadata = (Color) md;
	}
}
