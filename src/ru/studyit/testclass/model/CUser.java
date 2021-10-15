package ru.studyit.testclass.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class CUser {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "sex")
    private boolean sex;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy="owner", fetch = FetchType.EAGER)
    private List<COrder> orders;



    public boolean getSex()
    {
        return sex;
    }
    public void setSex(boolean sex)
    {
        this.sex = sex;
    }
    public UUID getId()
    {
        return id;
    }
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        int age = CUser.getAge(dateOfBirth);
        if (age<150 && age>=14)
            this.dateOfBirth = dateOfBirth;
    }
    public int getAge()
    {
        LocalDate now = LocalDate.now();
        return now.getYear()-this.dateOfBirth.getYear();
    }
    public static int getAge(LocalDate date)
    {
        LocalDate now = LocalDate.now();
        if (date==null)
            return now.getYear();
        return now.getYear()-date.getYear();
    }


    public String getLogin()
    {
        return login;
    }
    public void setLogin(String login)
    {
        if (login.length()<=50)
            this.login = login;
    }
    public CUser()
    {
        id = null;
        sex = true;
        dateOfBirth = LocalDate.now();
        login = "";
    }
    public CUser(String login, LocalDate dateOfBirth, boolean sex)
    {
        this(UUID.randomUUID(), login, dateOfBirth, sex);
    }
    public CUser(UUID id, String login, LocalDate dateOfBirth, boolean sex)
    {
        this.id = id;
        setLogin(login);
        setDateOfBirth(dateOfBirth);
        setSex(sex);
    }

    public List<COrder> getOrders() {
        return orders;
    }
}
