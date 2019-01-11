package app.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users4")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", length = 256)
    private String name;

    @Column(name = "user_login", length = 256, unique = true)
    private String login;

    @Column(name = "user_password", length = 256)
    private String password;

    @JsonManagedReference("roles4")
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity = Role.class)
    @JoinTable(name = "users_roles4",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login, String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    // возвращает коллекцию прав пользователя
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }


    //говорит что аккаунт актуален
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //говорит что аккаунт не заблокирован
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //говорит что учетные данные актуальны
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //говорит что аккаунт включен
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}

