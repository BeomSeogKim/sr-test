package sr.backend.test.domain.member;


import jakarta.persistence.*;
import lombok.Getter;
import sr.backend.test.domain.BaseEntity;

@Getter
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    protected Member() {
    }

    private Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public static Member of(String memberId, String password) {
        return new Member(memberId, password);
    }
}
