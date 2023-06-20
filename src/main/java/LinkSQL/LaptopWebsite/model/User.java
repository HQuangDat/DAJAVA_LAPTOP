package LinkSQL.LaptopWebsite.model;

import LinkSQL.LaptopWebsite.Validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Username must required")
    @Size(max = 50, message = "Username must less than 50 characters")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Password must required")
    private String password;

    @Column(name = "email", length = 50)
    @Email(message = "Email must required")
    @Size(max = 50, message = "Email must less than 50 characters")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @NotBlank(message = "Your name must required")
    @Size(max = 50, message = "Your name must less than 50 characters")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
