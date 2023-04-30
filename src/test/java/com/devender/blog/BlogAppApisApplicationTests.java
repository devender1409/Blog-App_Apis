package com.devender.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devender.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest(){
		String className = this.userRepo.getClass().getName().toString();
		String packageName = this.userRepo.getClass().getPackageName().toString();
		System.out.println(className);
		System.out.println("package name-> " + packageName);
	}

}

