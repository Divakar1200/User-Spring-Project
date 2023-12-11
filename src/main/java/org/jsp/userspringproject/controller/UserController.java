package org.jsp.userspringproject.controller;

import java.util.Scanner;

import org.jsp.userspringproject.UserConfig;
import org.jsp.userspringproject.dao.UserDao;
import org.jsp.userspringproject.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	
	static UserDao userDao;
	static Scanner sc = new Scanner(System.in);
	private static AnnotationConfigApplicationContext context;
	static{
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		userDao = context.getBean(UserDao.class);
	}
	
	public static void main(String[] args) {
		boolean b = true;
		while (b) {
			System.out.println("1.Save user");
			System.out.println("2.Update user");
			System.out.println("3.Find User By Id");
			System.out.println("4.Delete user By id");
			System.out.println("5.Verify User by id and password");
			System.out.println("6.Verify User by phone and password");
			System.out.println("7.Verify User by email and password");
			System.out.println("8.To close the application");
			System.out.println("Enter Your Choice");
			switch (sc.nextInt()) {
			case 1: {
				save();
				break;
			}
			case 2: {
				update();
				break;
			}
			case 3: {
				findById();
				break;
			}
			case 4: {
				delete();
				break;
			}
			case 5: {
				verifyById();
				break;
			}
			case 6: {
				verifyByPhone();
				break;
			}
			case 7: {
				verifyByEmail();
				break;
			}
			case 8: {
				System.out.println("Thank You!!!Application Closed");
				((AnnotationConfigApplicationContext)context).close();
				b = false;
			}
			}
		}
	}

	private static void verifyByEmail() {
		System.out.println("Enter Your email and password to verify");
		String email = sc.next();
		String password = sc.next();
		User u = userDao.verifyUser(email, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User id:" + u.getId());
			System.out.println("User Name:" + u.getName());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("--------------------------");
		} else {
			System.err.println("Invalid Email or Password");
		}
	}

	private static void verifyByPhone() {
		System.out.println("Enter Your Phone No. and password to verify");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = userDao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User id:" + u.getId());
			System.out.println("User Name:" + u.getName());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("--------------------------");
		} else {
			System.err.println("Invalid Phone or Password");
		}
	}

	private static void verifyById() {
		System.out.println("Enter Your Id and password to verify");
		int id = sc.nextInt();
		String password = sc.next();
		User u = userDao.verifyUser(id, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User id:" + u.getId());
			System.out.println("User Name:" + u.getName());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("--------------------------");
		} else {
			System.err.println("Invalid Id or Password");
		}
	}

	private static void delete() {
		System.out.println("Enter the User Id to delete");
		int id = sc.nextInt();
		boolean deleted = userDao.deletedbyid(id);
		if (deleted) {
			System.out.println("User Found and deleted");
		} else {
			System.out.println("You have entered an Invalid Id");
		}
	}

	private static void findById() {
		System.out.println("Enter the User Id to display details");
		int id = sc.nextInt();
		User u = userDao.findbyid(id);
		if (u!=null) {
			System.out.println("User with Id " + id + " found");
			System.out.println("User id:" + u.getId());
			System.out.println("User Name:" + u.getName());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email:" + u.getEmail());
			System.out.println("--------------------------");
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	private static void update() {
		System.out.println("Enter Id to update User");
		int id= sc.nextInt();
		System.out.println("Enter Your name,phone,email and password to register");
		User u = new User();
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = userDao.updateUser(u);
		System.out.println("user saved with Id:" + u.getId());
	}

	private static void save() {
		System.out.println("Enter Your name,phone,email and password to register");
		User u = new User();
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = userDao.saveUser(u);
		System.out.println("user saved with Id:" + u.getId());
	}
}
