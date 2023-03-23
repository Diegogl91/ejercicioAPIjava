package ejercicioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private UUID token = UUID.randomUUID();

    @JsonIgnoreProperties(value = {"user","id"}, allowSetters = true)
    @Column(nullable = false)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @Temporal(TemporalType.DATE)
    private Date lastLogin;

    private Boolean isActive;

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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getlastLogin() {
        return lastLogin;
    }

    public void setlastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
        Map<UUID,User> userMap = new HashMap<>();
        userMap.put(token,this);
    }
}
