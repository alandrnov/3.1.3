package _3_1_1.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    /*@JoinTable(
            name = "users_roles"
            , joinColumns = @JoinColumn(name = "users_id")
            , inverseJoinColumns = @JoinColumn(name = "roles_id")
    )*/
    private Set<Role> roles;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String cellphone;


    public User() {
    }

    public User(long id, String login, String password, Set<Role> roles, String firstName, String secondName, String cellphone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cellphone = cellphone;
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

}

