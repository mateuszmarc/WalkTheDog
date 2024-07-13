package com.mateuszmarcyk.walkthedog.registration.token;

import com.mateuszmarcyk.walkthedog.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Value("${tokenExpirationTime}")
    private int TOKEN_EXPIRATION_TIME;

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

    private Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime());

        calendar.add(Calendar.MINUTE, TOKEN_EXPIRATION_TIME);

        return new Date(calendar.getTime().getTime());
    }
}
