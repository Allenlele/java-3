package com.wds.java.concurrency.chapter5.first;

import java.util.ArrayList;
import java.util.List;

/**
 * 4、创建ProductListGenerator类，用于生产一些产品的列表
 * @author wds
 *
 */
public class ProductListGenerator {
	
	/*
	 * 5、实现generate方法，接受一个表示产品数量的参数，并返回生成的产品列表
	 */
	public List<Product> generate(int size){
		//6、创建返回结果对象
		List<Product> ret = new ArrayList<>();
		//7、生成产品列表，为它们指定一样的产品价格，例如10就挺合适的
		for(int i = 0; i < size; i++){
			Product product = new Product();
			product.setName("Product-" + i);
			product.setPrice(10);
			ret.add(product);
		}
		
		return ret;
	}
}
