package com.mateuszmarcyk.walkthedog.registration.token;

import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION_TIME = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "token")
    private String token;

    @Column(name = "expiration_time")
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = getTokenExpirationTime();
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = getTokenExpirationTime();
    }

    private static Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime());

        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);

        return new Date(calendar.getTime().getTime());
    }
}
