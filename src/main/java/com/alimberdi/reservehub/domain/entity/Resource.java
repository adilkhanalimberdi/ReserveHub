package com.alimberdi.reservehub.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Long capacity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
