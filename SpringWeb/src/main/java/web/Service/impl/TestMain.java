package web.Service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMain {

	public static void main(String[] args) {
		StoreClientImpl scl = new StoreClientImpl(new ObjectMapper());
		System.out.println(scl.getProduct(5).toString());
	}

}
