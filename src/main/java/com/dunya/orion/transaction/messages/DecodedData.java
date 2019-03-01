package com.dunya.orion.transaction.messages;

import java.util.ArrayList;

public class DecodedData extends ArrayList<ArrayList<String>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		this.forEach(pair -> {
			builder.append("[").append("action=").append(pair.get(0)).append(", data=").append(pair.get(1)).append("]");
		});
		builder.append("]");
		return builder.toString();
	}
	
	public String getData(int index) {
//		System.out.println("string: " + this.toString());
//		System.out.println("size: " + this.size());
		if(this.size() > 0 && this.get(index).size() > 0 && this.get(index).get(0) != null)
			return this.get(index).get(1);
		else
			return null;
	}

}
