package com.mkyong.core;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.solr.core.SolrOperations;

import com.mkyong.config.SpringMongoConfig;
import com.mkyong.model.User;
//import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		// For XML
		//ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		//SolrOperations solrOperation = (SolrOperations) ctx.getBean("solrTemplate");

		User user = new User("mkyong", "password123");

		// save
		mongoOperation.save(user);

		// now user object got the created id.
		System.out.println("1. user : " + user);

		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));

		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		System.out.println("2. find - savedUser : " + savedUser);

		// update password
		mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"),
				User.class);

		// find the updated user object
		User updatedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("mkyong")), User.class);

		System.out.println("3. updatedUser : " + updatedUser);

		// delete
		mongoOperation.remove(searchUserQuery, User.class);

		// List, it should be empty now.
		//List<User> listUser = mongoOperation.findAll(User.class);
		//System.out.println("4. Number of user = " + listUser.size());
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("SpringConfig.xml").getPath());
		com.mkyong.repo.BookRepo bookRepo = context.getBean(com.mkyong.repo.BookRepo.class);
		com.mkyong.model.Book hobbit = new com.mkyong.model.Book();
		hobbit.setId("3");
		hobbit.setTitle("Hobbit");
		hobbit.setDescription("Prelude to LOTR");
		//bookRepo.save(hobbit);
		mongoOperation.save(hobbit);
		//solrOperation.saveBean("book", hobbit);		
		
		System.out.println("#@$@ bookRepo.findOne(3l) ="+bookRepo.findOne(3l));
		System.out.println("@!$@ bookRepo.findByBookTitle(\"Hobbit\") ="+bookRepo.findByBookTitle("Hobbit"));

		context.close();


	}

}