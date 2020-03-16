package com.dksoft.formshiftserver.Repository;

import com.dksoft.formshiftserver.Model.UserFacebookData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFacebookDataRepository extends JpaRepository<UserFacebookData, Integer> {
    UserFacebookData findDistinctByUserId(int userId);
}
