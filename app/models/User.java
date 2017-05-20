package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by An on 5/14/2017.
 */
@Entity
@Table(name = "e_user")
@NamedQueries({
        @NamedQuery(name = "User.getByUsernameAndHashPassword",
                query = "SELECT u from User u WHERE u.username = :username AND u.hashPassword = :hashPassword"),
        @NamedQuery(name = "User.getByUsername",
                query = "SELECT u from User u WHERE u.username = :username")

})
public class User {
    @Id
    @Constraints.Required
    private Integer id;
    private String username;
    @Column(name = "hash_password")
    private String hashPassword;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=
            @JoinColumn(name="e_user", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="role", referencedColumnName="id")
    )
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }


}
