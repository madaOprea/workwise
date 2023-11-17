package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(schema = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "transactionId_uuid")
    @GenericGenerator(name="transactionId_uuid", strategy = "uuid")
    @Column(name="transactionId_uuid")
    private String transactionId;

    @Column
    private String user1;

    @Column
    private String user2;
}
