package com.dksoft.formshiftserver.Model;

import javax.persistence.*;

@Entity
@Table(name = "formshift_users_facebook_data")
public class UserFacebookData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    public int userId;

    @Column(name = "facebook_id")
    public String facebookId;

    @Column(name = "email")
    public String email;

    @Column(name = "real_name")
    public String realName;

    @Column(name = "icon_url")
    public String iconUrl;

    public UserFacebookData(int userId, String facebookId, String email, String realName, String iconUrl) {
        this.userId = userId;
        this.facebookId = facebookId;
        this.email = email;
        this.realName = realName;
        this.iconUrl = iconUrl;
    }

    public UserFacebookData(){}
}
