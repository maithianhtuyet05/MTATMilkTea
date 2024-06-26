package com.fpt.mtatmilkteaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="\"order\"")
public class Order implements Serializable {

    @Id
    private String id; // Sử dụng O + getTime()

    private String address;
    private String phone;

    @ColumnDefault("0")
    private int payment; // 0: chưa có gì, 1: tiền mặt, 2: trực tuyến, 3: lỗi(nếu có)

    @ColumnDefault("0")
    private int status; // 0: chưa có gì hết, 1: chưa làm, 2: vận chuyển, 3: hoàn thành, 4: lỗi(nếu có)

    @Column(length = 1000)
    private String noteOrder;

    @ColumnDefault("0")
    private long totalPrice;

    @ColumnDefault("0")
    private int shipping;

    @ColumnDefault("0")
    private long memberVip;

    @ColumnDefault("true")
    private boolean team;

    @ColumnDefault("false")
    private boolean rating;

    @ColumnDefault("0") // 0: chưa có gì hết, 1: thông báo để hiển thị, 2: đã xem
    private int notification;

//    @CreationTimestamp
    private Date createdAt;

    @CreationTimestamp
    private Date updatedAt;

    private Date deletedAt;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnoreProperties("orders")
    private User userId;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "orderId",
            cascade = CascadeType.ALL
    )
    private Collection<OrderDetail> orderDetails;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private Collection<GroupMember> groupMembers;

    public Order(String id, String address, String phone, int payment, int status, String noteOrder, long memberVip, User userId, int totalPrice, int shipping) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
        this.status = status;
        this.noteOrder = noteOrder;
        this.memberVip = memberVip;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.shipping = shipping;
    }

    public Order(String id, String address, String phone, int payment, int status, String noteOrder, long memberVip, User userId, int totalPrice, int shipping, Date createdAt) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
        this.status = status;
        this.noteOrder = noteOrder;
        this.memberVip = memberVip;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.shipping = shipping;
        this.createdAt = createdAt;
    }
}
