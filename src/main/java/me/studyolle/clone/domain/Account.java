package me.studyolle.clone.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private boolean isEmailVerified;

    private String emailCheckToken;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDateTime joinedAt;

    private String biography;

    private String url;

    private String job;

    private String location;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private boolean isCreatedNotifiedByEmail;

    private boolean isCreatedNotifiedByWeb;

    private boolean isEnrollmentResultByEmail;

    private boolean isEnrollmentResultByWeb;

    private boolean isStudyUpdatedByEmail;

    private boolean isStudyUpdatedByWeb;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID()
                                   .toString();
    }
}
