package com.devender.blog.repositories;




import org.springframework.data.jpa.repository.JpaRepository;

import com.devender.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
