package com.reclebooks.recle;


import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.UserDto;
import com.reclebooks.recle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


/**
 * InitDb
 * username password nickname
 * admin    1234    nickadmin   admin
 * yeongsang 1234   yeongsang   USER
 * david    1234    david USER
 * */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private UserService userService;
        private final PasswordEncoder passwordEncoder;
        public void dbInit(){
            String encode = passwordEncoder.encode("1234");
            //admin 계정
            User user = createUser("admin", encode);

            UserInfo userInfo = createUserInfo("admin");

            user.setUserInfo(userInfo);

            Authority authority = new Authority();
            authority.setAuthorityType(AuthorityType.ROLE_ADMIN);

            UserAuthority userAuthority = createUserAuthority(user, authority);

            em.persist(authority);
            em.persist(userAuthority);
            em.persist(user);

            //일반 계정
            User user1 = createUser("yeongsang", encode);


            UserInfo userInfo1 = createUserInfo("yeongsang");

            user1.setUserInfo(userInfo1);

            Authority authority1 = new Authority();
            authority1.setAuthorityType(AuthorityType.ROLE_USER);

            UserAuthority userAuthority1 = createUserAuthority(user1, authority1);

            em.persist(authority1);
            em.persist(userAuthority1);
            em.persist(user1);
            //
            User user2 = createUser("david", encode);


            UserInfo userInfo2 = createUserInfo("david");

            user2.setUserInfo(userInfo2);

            Authority authority2 = new Authority();
            authority2.setAuthorityType(AuthorityType.ROLE_USER);

            UserAuthority userAuthority2 = createUserAuthority(user2, authority2);

            em.persist(authority2);
            em.persist(userAuthority2);
            em.persist(user2);
        }

        private UserAuthority createUserAuthority(User user, Authority authority) {
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setAuthority(authority);
            userAuthority.setUser(user);
            return userAuthority;
        }

        private UserInfo createUserInfo(String admin) {
            UserInfo userInfo = new UserInfo() ;
            userInfo.setNickName(admin);
            return userInfo;
        }

        private User createUser(String admin, String encode) {
            User user = new User();
            user.setUsername(admin);
            user.setPassword(encode);
            user.setActivated(true);
            return user;
        }

    }
}
