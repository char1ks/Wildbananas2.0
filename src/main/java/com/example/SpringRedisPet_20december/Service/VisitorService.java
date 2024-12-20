package com.example.SpringRedisPet_20december.Service;

import com.example.SpringRedisPet_20december.Model.Visitor;
import com.example.SpringRedisPet_20december.Redis.RedisPublisher;
import com.example.SpringRedisPet_20december.Repository.VisitorRepository;
import com.example.SpringRedisPet_20december.Security.VisitorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class VisitorService implements UserDetailsService {

    @Autowired
    private RedisPublisher publisher;

    @Autowired
    private VisitorRepository operations;

    public void saveVisitor(Visitor visitor){
        operations.save(visitor);
    }
    public Visitor googleAuth() {
        System.out.println(123);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            System.out.println(321);
            OAuth2User oAuth2User = oauthToken.getPrincipal();
            System.out.println(123);
            if (oAuth2User != null) {
                Map<String, Object> attributes = oAuth2User.getAttributes();
                System.out.println("here4");

                String givenName = (String) attributes.get("given_name");
                String familyName = (String) attributes.get("family_name");
                String username = "";
                if (givenName != null) {
                    username += givenName; // Добавляем имя, если оно не null
                }
                if (familyName != null) {
                    if (!username.isEmpty()) {
                        username += " "; // Добавляем пробел, если имя уже добавлено
                    }
                    username += familyName; // Добавляем фамилию, если она не null
                }
                Optional<Visitor> findOrNo = Optional.ofNullable(operations.findByUsername(username));
                if(findOrNo.isEmpty()) {
                    Visitor user = new Visitor();
                    user.setUsername(username);
                    publisher.publish("Некто '"+username+"' пытается зарегистрировать аккаунт через сервис 'Google'");
                    return user;
                }
                publisher.publish("Некто '"+username+"' пытается войти в аккаунт через сервис 'Google'");
                return findOrNo.get();
            }
        }
        System.out.println(421);
        return new Visitor();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Visitor>findVisitor= Optional.ofNullable(operations.findByUsername(username));
        return new VisitorDetails(findVisitor.get());
    }
}
