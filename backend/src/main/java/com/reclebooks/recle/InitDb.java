//package com.reclebooks.recle;
//
//
//import com.reclebooks.recle.domain.*;
//import com.reclebooks.recle.dto.categorydto.CategoryDto;
//import com.reclebooks.recle.dto.postdto.PostDto;
//import com.reclebooks.recle.repository.UserRepository;
//import com.reclebooks.recle.service.CategoryService;
//import com.reclebooks.recle.service.PostService;
//import com.reclebooks.recle.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * InitDb
// * username password nickname
// * admin    1234    nickadmin   admin
// * yeongsang 1234   yeongsang   USER
// * david    1234    david USER
// * */
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() throws Exception{
//        initService.dbUserInit();
//        initService.dbPostInit();
//    }
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//        private final UserService userService;
//        private final UserRepository userRepository;
//        private final CategoryService categoryService;
//        private final PostService postService;
//        private final PasswordEncoder passwordEncoder;
//        public void dbUserInit(){
//            String encode = passwordEncoder.encode("1234");
//            //admin 계정
//            User user = createUser("admin", encode);
//
//            UserInfo userInfo = createUserInfo("admin");
//
//            user.setUserInfo(userInfo);
//
//            Authority authority = new Authority();
//            authority.setAuthorityType(AuthorityType.ROLE_ADMIN);
//
//            UserAuthority userAuthority = createUserAuthority(user, authority);
//
//            em.persist(authority);
//            em.persist(userAuthority);
//            em.persist(user);
//
//            //일반 계정
//            User user1 = createUser("yeongsang", encode);
//
//
//            UserInfo userInfo1 = createUserInfo("yeongsang");
//
//            user1.setUserInfo(userInfo1);
//
//            Authority authority1 = new Authority();
//            authority1.setAuthorityType(AuthorityType.ROLE_USER);
//
//            UserAuthority userAuthority1 = createUserAuthority(user1, authority1);
//
//            em.persist(authority1);
//            em.persist(userAuthority1);
//            em.persist(user1);
//            //
//            User user2 = createUser("david", encode);
//
//
//            UserInfo userInfo2 = createUserInfo("david");
//
//            user2.setUserInfo(userInfo2);
//
//            Authority authority2 = new Authority();
//            authority2.setAuthorityType(AuthorityType.ROLE_USER);
//
//            UserAuthority userAuthority2 = createUserAuthority(user2, authority2);
//
//            em.persist(authority2);
//            em.persist(userAuthority2);
//            em.persist(user2);
//        }
//
//        public void dbPostInit() throws Exception{
//            User user = userRepository.findOneWithuserAuthoritiesByUsername("yeongsang").get();
//
//            PostDto postDto = new PostDto();
//
//            postDto.setUserId(user.getId());
//
//            postDto.setTitle("책팜");
//            postDto.setDescription("책팔아용");
//            postDto.setPrice("10000");
//
//            postDto.setBookName("jpa");
//            postDto.setBookAuthor("김영한");
//            postDto.setBookPrice("200000");
//            postDto.setBookPublisher("조은출판");
//            postDto.setIsbn("2315");
//            postDto.setPublishDate("20200303");
//
/////////////////////////////////////////
//
//            PostDto postDto1 = new PostDto();
//
//            postDto1.setUserId(user.getId());
//
//            postDto1.setTitle("토비의스프링");
//            postDto1.setDescription("책팔아용");
//            postDto1.setPrice("15000");
//
//            postDto1.setBookName("토비의스프링");
//            postDto1.setBookAuthor("토비");
//            postDto1.setBookPrice("25000");
//            postDto1.setBookPublisher("조은출판");
//            postDto1.setIsbn("8455");
//            postDto1.setPublishDate("20201212");
//
//            List<MultipartFile> fileListEx = new ArrayList<>();
//
//            //when
//            Long postId = postService.createPost(postDto, fileListEx);
//            Long postId1 = postService.createPost(postDto1, fileListEx);
//        }
//
//        private UserAuthority createUserAuthority(User user, Authority authority) {
//            UserAuthority userAuthority = new UserAuthority();
//            userAuthority.setAuthority(authority);
//            userAuthority.setUser(user);
//            return userAuthority;
//        }
//
//        private UserInfo createUserInfo(String admin) {
//            UserInfo userInfo = new UserInfo() ;
//            userInfo.setNickName(admin);
//            return userInfo;
//        }
//
//        private User createUser(String admin, String encode) {
//            User user = new User();
//            user.setUsername(admin);
//            user.setPassword(encode);
//            user.setActivated(true);
//            return user;
//        }
//
//    }
//
//
//}
