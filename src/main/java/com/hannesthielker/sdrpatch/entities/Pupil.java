package com.hannesthielker.sdrpatch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pupil extends BaseEntity{

    private String name;

    @ManyToOne(optional = false)
    private Course course;

}
