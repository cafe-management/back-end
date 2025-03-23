package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @Column(length = 20)
    private String orderId;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private double shippingCost;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private OrderType orderType;

    @Column(columnDefinition = "double default 0.0")
    private Double rushFee = 0.0;

    @Column(columnDefinition = "double default 0.0")
    private Double customsFee = 0.0;
}
