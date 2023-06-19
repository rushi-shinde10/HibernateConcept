package com.bmc.utility;

import java.util.Scanner;

import com.bmc.entity.Product;

public class ProductUtility {

	public static Product prepareProductData(Scanner scanner) {
		Product product=null;
		
		try {
			
			System.out.println("Enter Product ID");
			long productId = scanner.nextLong();
			
			if(productId<=0) {
				System.out.println("Product Id is Invalid");
				return null;
			}
			
			System.out.println("Enter Product Name");
			String productName = scanner.next();
			
			for (char c : productName.toCharArray()) { 
			if (!Character.isAlphabetic(c)){
			System.err.println("INVALID Product Name ! Please write it in Alphabetic Format"); 
			break; }
			}
			
			
			System.out.println("Enter Supplier Id");
			long supplierId = scanner.nextLong();
			
			System.out.println("Enter Category Id");
			long categoryId = scanner.nextLong();
			
			System.out.println("Enter Product Quantity");
			int productQTY = scanner.nextInt();
			
			System.out.println("Enter Product Price");
			double productPrice = scanner.nextDouble();
			
			product=new Product(productId, productName, supplierId, categoryId, productQTY, productPrice);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		boolean validateProduct = validateProduct(product);
		if (validateProduct==true) {
			return product;
		} else {
            return null;
		}
		
	}
		
		/*
		 * String string = Long.toString(productId);
		 * for (char c : string.toCharArray()) { if (!Character.isDigit(c)){
		 * System.out.println("INVALID Id"); break; } }
		 * 
		 *  if (!productId.matches("[0-9]+")) { 
			  
			  System.out.println("Invalid number"); }
		 */
		/*
		 * for (char c : productName.toCharArray()) { if (!Character.isAlphabetic(c)){
		 * System.out.println("INVALID Product Name"); break; } }
		 * if (productName== null ) { System.out.println("Enter Valid Alphabetic Name");
		 * return null; }
		 * if(supplierId<=0) { System.out.println("Enter valid Number as Supplier Id");
		 * return null; }
		 * 
		 * if(categoryId<=0) { System.err.println("Enter valid Number as category Id");
		 * return null; }
		 * 
		 * if(productQTY<=0) { System.err.println("Enter valid Number as productQTY");
		 * return null; }
		 * 
		 * if(productPrice<=0) {
		 * System.err.println("Enter valid Number as productPrice"); return null; }
		 */
		
		/* boolean isValid = validateProduct(product); */
	
	
	public static boolean validateProduct(Product product) {
		boolean isValid=true;
	if(product.getProductId()<=0) {
		isValid=false;
	}
		if(product.getProductName()==null) {
			isValid=false;
			return isValid;
		}
		
		if(product.getSupplierId()<=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getCategoryId()<=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getProductQTY()<=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getProductPrice()<=0) {
			isValid=false;
			return isValid;
		}
		
		return isValid;
	}
	

}
