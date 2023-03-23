package ejercicioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Email
    private String email;

//    @Column(nullable = false)
//    @NotEmpty
//    private String token;

    @JsonIgnoreProperties(value = {"user","id"}, allowSetters = true)
    @Column(nullable = false)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public String getname() {
        return name;
    }

    public String getpassword() {
        return password;
    }

    public String getemail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setemail(String email) {
        this.email = email;
    }
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phone) {
        this.phones.clear();
        phone.forEach(this::addPhone);
    }
    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setUser(this);
    }

    public void removePhone(Phone phone) {
        this.phones.remove(phone);
        phone.setUser(null);
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;

    }

    public Date getCreateAt() {
        return createAt;
    }
    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }


}
