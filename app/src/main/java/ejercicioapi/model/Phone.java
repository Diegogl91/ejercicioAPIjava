package ejercicioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;

    @JsonIgnoreProperties(value = {"user"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;
    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }

    //Para saber si el phones que se quiere eliminar existe en el listado de phones comparandolo con el id.
    public boolean equals(Object o) {
    if(this == o){
        return true;
    }
    if (!(o instanceof Phone)){
        return false;
    }
    Phone p = (Phone) o;
    return this.id !=null && this.id.equals(p.getId());
    }
}
