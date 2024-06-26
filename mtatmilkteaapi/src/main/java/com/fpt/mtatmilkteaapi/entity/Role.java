package com.fpt.mtatmilkteaapi.entity;


import com.fpt.mtatmilkteaapi.config.ERole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ERole name;
}
