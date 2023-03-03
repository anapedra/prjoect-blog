package com.anapedra.blogbackend.config;

import com.anapedra.blogbackend.entities.*;
import com.anapedra.blogbackend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.anapedra.blogbackend.entities.enuns.UserStatus.ACTIVE;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public TestConfiguration(UserRepository userRepository, RoleRepository roleRepository, PostRepository postRepository, CategoryRepository categoryRepository, CommentRepository commentRepository, ReplyRepository replyRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<User> users=new ArrayList<>();
        List<Role> roles=new ArrayList<>();
        List<Post> posts=new ArrayList<>();
        List<Category>categories=new ArrayList<>();
        List<Comment>comments=new ArrayList<>();
        List<Reply>replies=new ArrayList<>();

        ;
        Role role1=new Role(null,"ROLE_ADMIN");
        Role role2=new Role(null,"ROLE_VISIT");
        Role role3=new Role(null,"ROLE_ADMIN_PRINCIPAL");

        roles.addAll(Arrays.asList(role1,role2,role3));
        roleRepository.saveAll(roles);

        User user1=new User(null,"Alex","Martins","alex@gmail.com","$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG",ACTIVE);
        User user2=new User(null,"Maria","Brotas","maria@gmail.com","$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG",ACTIVE);
        User user3=new User(null,"Pedro","Lopes","pedreo@gmail.com","$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG",ACTIVE);

        users.addAll(Arrays.asList(user1,user2,user3));
        userRepository.saveAll(users);

        user1.getRoles().add(role1);
        user2.getRoles().add(role2);
        user3.getRoles().add(role1);

        users.addAll(Arrays.asList(user1,user2));
        userRepository.saveAll(users);

        Category category1=new Category(null,"Entreterimento");
        Category category2=new Category(null,"Educativo");
        Category category3=new Category(null,"Tecnologia");

        categories.addAll(Arrays.asList(category1,category2,category3));
        categoryRepository.saveAll(categories);

        Post post1=new Post(null,"É a idade é uma fator determinante para apredende a programar?","Eu diria que a idade é um fator importante, mas não determinante. É indiscutível que na infância e na juventude o indivíduo tem uma pré-disposição para o aprendizado,qualquer,aprendizado.As crianças e jovens possuen, sim, vantagens em relação a outras pessoas que não possuem esses benefícios que contribuem para uma aprendizagem efetiva. No entanto acredito que a determinação, vontade, paciencia e a perceverança, além da humildade e da capacidade de aceitar que pouco sabemos ou nada sabemos em relação ao mundo diversificado de conhecimentos. E esses fatores independe de faixa-hetária e deveriam ser estimulado em todos indivíduos independente da idade,considerando que a vida um grende aprendizado.",
                LocalDate.now(),LocalDate.now(),user1);
        Post post2=new Post(null,"Is age a determining factor in learning to program?","I would say that age is an important factor, but not a determining one. It is indisputable that in childhood and youth, the individual has a pre-disposition for learning, whatever, learning. Children and young people do have advantages over other people who do not have these benefits that contribute to effective learning. However, I believe that determination, will, patience and perseverance, in addition to humility and the ability to accept that we know little or nothing in relation to the diverse world of knowledge. And these factors are independent of age group and should be encouraged in all individuals regardless of age, considering that life is a great learning experience.",LocalDate.now(),LocalDate.now(),user1);
        posts.addAll(Arrays.asList(post1,post2));
        postRepository.saveAll(posts);


        post1.getCategories().add(category1);
        post2.getCategories().add(category2);
        post1.getCategories().add(category3);

        posts.addAll(Arrays.asList(post1,post2));
        postRepository.saveAll(posts);

        Comment comment1=new Comment(null,"Muito bom mesto!", LocalDate.now(),LocalDate.now(),post1,user2);
        Comment comment2=new Comment(null,"Muito bom ruin!",  LocalDate.now(), LocalDate.now(),post1,user2);
        Comment comment3=new Comment(null,"Muito bom mriste!",LocalDate.now(), LocalDate.now(),post2,user1);
        Comment comment4=new Comment(null,"Muito bom testo!", LocalDate.now(), LocalDate.now(),post1,user1);

        comments.addAll(Arrays.asList(comment1,comment2,comment3,comment4));
        commentRepository.saveAll(comments);

        Reply reply1=new Reply(null,"Obrigada",user2,comment1, LocalDate.now(), LocalDate.now());
        Reply reply2=new Reply(null,"Obrigada",user1,comment2, LocalDate.now(), LocalDate.now());
        Reply reply3=new Reply(null,"Obrigada",user1,comment2, LocalDate.now(), LocalDate.now());
        Reply reply4=new Reply(null,"Obrigada",user2,comment4, LocalDate.now(), LocalDate.now());

        replies.addAll(Arrays.asList(reply1,reply2,reply3,reply4));
        replyRepository.saveAll(replies);






    }
}
