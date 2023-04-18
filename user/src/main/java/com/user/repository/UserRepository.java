package com.user.repository;

import com.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {

    User findUserById(String userId);
    @Query(value = "select * from users order by created_date desc LIMIT 1", nativeQuery = true)
    User findTheLastUserAdded();
    void deleteById(String id);
    User save(User user);
    User findUserByEmail(String email);
    User getByEmail(String email);
}
