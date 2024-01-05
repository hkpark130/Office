package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "histories")
@Table(name = "histories")
@Getter
public class Histories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="approval_id", referencedColumnName = "id")
    private Approvals approvalId;

    @ManyToOne
    @JoinColumn(name ="device_id", referencedColumnName = "id")
    private Devices deviceId;

    public Histories() {

    }

    @Builder
    public Histories(Long id, Approvals approvalId, Devices deviceId) {
        this.id = id;
        this.approvalId = approvalId;
        this.deviceId = deviceId;
    }
}
