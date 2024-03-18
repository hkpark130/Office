package kr.co.direa.office.vo;

import lombok.Data;

@Data
public class DevicesRedponseVo {
    private Long amount;
    private String name;
    private String img;

    public DevicesRedponseVo(Long deviceCount, String name, String img) {
        this.amount = deviceCount;
        this.name = name;
        this.img = img;
    }
}
