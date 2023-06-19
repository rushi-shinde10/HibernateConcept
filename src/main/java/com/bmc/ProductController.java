package com.bmc;

import java.util.List;
import java.util.Scanner;

import com.bmc.dao.ProductDao;
import com.bmc.entity.Product;
import com.bmc.entity.ProductModel;
import com.bmc.utility.ProductUtility;

public class ProductController {
public static void main(String[] args) {
	ProductDao productDao=new ProductDao();
	int choice;
	char c;
	Product product=null;
	Scanner scanner=new Scanner(System.in);
	
	do {
		System.out.println("Press 1 for Data save");
		System.out.println("Press 2 for Get Product By Id");
		System.out.println("Press 3 To delete Product");
		System.out.println("Press 4 to Update Product");
		System.out.println("Press 5 for getAllProducts");
		System.out.println("Press 6 for sort product");
		System.out.println("Press 7 for Restriction Ex");
		System.out.println("Press 8 for Projection Example");
		System.out.println("Press 9 for Query Example");

		
		choice=scanner.nextInt();
		
		
		switch (choice) {
		case 1:
			 product= ProductUtility.prepareProductData(scanner);
			if (product!=null) {
			String msg = productDao.addProduct(product);
		    System.out.println(msg);
			} else {
				
				System.out.println("Product Not Valid");
			}
			break;
			
		case 2:

			System.out.println("Enter Product Id");
			long productId = scanner.nextLong();
			product= productDao.getProductById(productId);

			if(product!=null) {
				
				System.out.println(product);
			}else {
				
				System.out.println("Product Is Not Found for productId "+productId);
			}
			
			break;

		case 3:
			System.out.println("Enter Product Id To delete Product");
			 productId = scanner.nextLong();
			 String msg = productDao.deleteProductById(productId);
			 System.out.println(msg);
			break;
			
		case 4:
			 product= ProductUtility.prepareProductData(scanner);
			 if (product!=null) {
				msg=productDao.updateProduct(product);
				System.out.println(msg);
			} else {
             System.out.println("Product Not Valid");
			}
			break;	
			
		case 5:
			List<Product> list = productDao.getAllProducts();
			if (list.isEmpty() || list==null) {
				System.out.println("Products Are not Found");
			}else {
				for (Product product1 : list) {
					System.out.println(product1);
				}
			}
			break;
			
		case 6:
			
			System.out.println("Enter Product Order Type");
			String orderType = scanner.next();
			System.out.println("Enter Property Name");
			String propertyName = scanner.next();
			
			list = productDao.sortProducts(orderType, propertyName);
			if (list.isEmpty() || list==null) {
				System.out.println("Products Are not Found");
			}else {
				for (Product product1 : list) {
					System.out.println(product1);
				}
			}
			break;
            
		
		case 7:
			
			list = productDao.restrictionExp();
			if (list.isEmpty() || list==null) {
				System.out.println("Products Are not Found");
			}else {
				for (Product product1 : list) {
					System.out.println(product1);
				}
			}
			break;
			
		case 8:
			list=productDao.projectionExp();
			if (list.isEmpty() || list==null) {
				System.out.println("Products Are not Found");
			}else {
				for (Product product1 : list) {
					System.out.println(product1);
				}
			}
			break;
			
		case 9:

			List<ProductModel> queryExp = productDao.queryExp();
			if (queryExp.isEmpty() || queryExp==null) {
				System.out.println("Products Are not Found");
			}else {
				for (ProductModel productModel : queryExp) {
					System.out.println(productModel);
				}
			}
			
			
			break;
			
			
		default:
			System.out.println("Invalid Data");
			break;
		}
		
		System.out.println("Do You Want To Continue Y/N");
		c=scanner.next().charAt(0);
		
	} while (c=='Y' || c=='y');
	System.out.println("App Terminated");
}
	
	
	
}
