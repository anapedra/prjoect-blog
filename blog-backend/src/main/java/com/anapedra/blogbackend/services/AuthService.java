package com.anapedra.blogbackend.services;


import com.anapedra.blogbackend.entities.User;
import com.anapedra.blogbackend.repositories.UserRepository;
import com.anapedra.blogbackend.services.exeptuonservice.ForbiddenException;
import com.anapedra.blogbackend.services.exeptuonservice.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {



    private final UserRepository userRepository;
    public AuthService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User authenticated(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
        }
        catch (Exception e){
            throw new UnauthorizedException("Invalid User!");
        }
    }


    public void validateSelfOrAdmin(Long id){
        User user=authenticated();
        if (!user.getId().equals(id) && !user.hasHole( "ROLE_ADMIN")){
            throw new ForbiddenException("Access denied");
        }

    }
    public void validateAdmin(){
        User user=authenticated();
        if (!user.hasHole( "ROLE_ADMIN")){
            throw new ForbiddenException("Access denied");
        }

    }
    public void validateSelf(Long id){
        User user=authenticated();
        if (!user.getId().equals(id)){
            throw new ForbiddenException("Access denied");
        }

    }

}
