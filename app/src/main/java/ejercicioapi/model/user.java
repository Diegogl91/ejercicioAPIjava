package ejercicioapi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
public class user {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String email;

    @OneToMany
    private List<Phones> phones;

    public static class Phones {

    }


    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;


    public user(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

    }

    public user() {

    }


    public String getname() {
        return name;
    }

    public String getpassword() {
        return password;
    }

    public String getemail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
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
}
