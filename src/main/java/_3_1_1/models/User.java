package _3_1_1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String cellphone;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authProvider;


    @ManyToMany(fetch = FetchType.LAZY)

    private Set<Role> roles;


    public User() {
    }

    public User(long id, String login, String password, Set<Role> roles, String firstName, String secondName, String cellphone, AuthenticationProvider authProvider) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cellphone = cellphone;
        this.authProvider = authProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getRoles() {
        StringBuilder s = new StringBuilder();

        for (Role r : roles) {
            if (s.length() != 0) {
                s.append(", ");
            }
            s.append(r.getRole());
        }
        return s.toString();
    }

    @JsonProperty
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
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

    public boolean isAdmin() {
        return this.getRoles().contains("ROLE_ADMIN");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", roles=" + roles + '\'' +
                ", authProvider=" + authProvider +
                '}';
    }
}

