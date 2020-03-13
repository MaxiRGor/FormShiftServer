package com.dksoft.formshiftserver.Repository;

import com.dksoft.formshiftserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
   // List<User> findAllByIdNotNull(int id);
    User findById(int id);

}
