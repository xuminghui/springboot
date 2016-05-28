package com.artbrain.dao;

import com.artbrain.constants.Queries;
import com.artbrain.entity.User;
import com.artbrain.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsServiceDAO implements UserDetailsService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String ROLE_PREFIX = "ROLE_";

  @Override
  public UserDetails loadUserByUsername(final String username)
      throws UsernameNotFoundException {
    User user = loadUserEntityByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username + " not found");
    }
    return new UserDetails() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(user.getRole()));
        return auths;
      }

      @Override
      public String getPassword() {
        return user.getPassword();
      }

      @Override
      public String getUsername() {
        return username;
      }

      @Override
      public boolean isAccountNonExpired() {
        return true;
      }

      @Override
      public boolean isAccountNonLocked() {
        return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }

      @Override
      public boolean isEnabled() {
        return true;
      }
    };
  }

  public enum ROLE {
    ADMIN("ADMIN"), USER("USER");

    private String role;

    ROLE(String role) {
      this.role = role;
    }

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    @Override
    public String toString() {
      return ROLE_PREFIX + role;
    }

  }

  public void saveUser(User user) throws Exception{
    jdbcTemplate.update(Queries.INSERT_NEW_USER, new Object[]{user.getUsername(), user.getPassword(), ROLE.USER.toString()});
  }

  public void deleteUser(String username) throws Exception{
    jdbcTemplate.update(Queries.DELETE_USER_BY_USERNAME, new Object[]{username});
  }

  public User loadUserEntityByUsername(String username){
    List<User> users = jdbcTemplate.query(Queries.LOAD_USER_BY_USERNAME, new Object[]{username}, (rs, rowNum) -> Mapper.mapUser(rs, rowNum));
    if(users == null || users.size() < 1){
      return null;
    }else{
      return users.get(0);
    }
  }

  public List<User> loadAllUsers() throws Exception{
    return jdbcTemplate.query(Queries.LOAD_ALL_USERS, (rs, rowNum) -> Mapper.mapUser(rs, rowNum));
  }

  public void updateUser(User user) throws Exception{
    jdbcTemplate.update(Queries.UPDATE_USER_BY_USERNAME, new Object[]{user.getPassword(), user.getRole(), user.getUsername()});
  }

}
