package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "device_tag")
@Table(name = "device_tag")
@Getter
public class DeviceTag extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="device_id")
    private Devices device;

    @ManyToOne
    @JoinColumn(name ="tag_id")
    private Tags tag;

    public DeviceTag() {

    }

    @Builder
    public DeviceTag(Long id, Devices device, Tags tag) {
        this.id = id;
        this.device = device;
        this.tag = tag;
    }

    public DeviceTag(Devices device, Tags tag) {
        this.device = device;
        this.tag = tag;
    }
}
