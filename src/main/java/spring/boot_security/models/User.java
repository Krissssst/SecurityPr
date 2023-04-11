package spring.boot_security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_t")
public class User {
    @Id
    private int id;
    @NotEmpty(message = "Name should not be empty ")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Min(value = 2, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;
    @NotEmpty(message = "SurName should not be empty")
    @Column(name = "surname")
    private String surname;
    @Column(name = "password")
    private String password;

    public User(String name, int age, String surname, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
