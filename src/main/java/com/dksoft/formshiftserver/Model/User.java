package com.dksoft.formshiftserver.Model;

import javax.persistence.*;

@Entity
//@Table(name = "questions")
@Table(name = "formshift_users")

public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    public Integer id;

    @Column(name = "facebook_id")
    public String facebookId;

    @Column(name = "email")
    public String email;

    @Column(name = "name")
    public String name;

    public User (String facebookId, String email, String name ){
        this.facebookId = facebookId;
        this.email = email;
        this.name = name;
    }

    public User(){}
}
