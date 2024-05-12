package com.rajdeep.blogapi;

import com.rajdeep.blogapi.config.AppConstants;
import com.rajdeep.blogapi.controller.RoleController;
import com.rajdeep.blogapi.daoRepositories.RoleDao;
import com.rajdeep.blogapi.modelEntity.RoleModel;
import com.rajdeep.blogapi.payloadData.RolePayload;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@ComponentScan

// for swagger
//@OpenAPIDefinition(info = @Info(title = "Blogging Application",
//		version = "1.0",
//		description = "This project is developed by Rajdeep Nagar"))

public class BlogApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);

		System.out.println("Hello world1");

	}

	 @Bean
//	Beans are objects managed by the Spring IoC (Inversion of Control) container.
//	The @Bean annotation tells Spring to manage the lifecycle of the
//	returned object (in this case, a ModelMapper instance).

	 public ModelMapper modelMapper(){

		 return new ModelMapper();

	 }

//	The ModelMapper is a library for mapping objects to each other.
//	It helps in transferring data between different layers of an application,
//	such as DTOs (Data Transfer Objects) and entities.


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	RoleDao roleDao;

	@Override
	public void run(String... args) throws Exception {

		try {

			RoleModel roleModel1=new RoleModel();

			roleModel1.setId(AppConstants.NORMAL_USER);
			roleModel1.setName("NORMAL");

			RoleModel roleModel2=new RoleModel();
			roleModel2.setId(AppConstants.ADMIN_USER);
			roleModel2.setName("ADMIN");

			this.roleDao.save(roleModel1);
			this.roleDao.save(roleModel2);

			System.out.println("NORMAL and ADMIN role created");

		} catch (Exception e) {
			System.out.println("Issue with creating NORMAL and ADMIN ROLE");
			throw new RuntimeException(e);
		}


	}
}
