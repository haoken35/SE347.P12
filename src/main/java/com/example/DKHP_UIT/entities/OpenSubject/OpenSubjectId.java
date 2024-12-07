package com.example.DKHP_UIT.entities.OpenSubject;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.DKHP_UIT.entities.Subject;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenSubjectId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NotNull
    private int hocKy;

    @NotNull
    private int nam; // 2024, when we display for the client, we will show nam - (nam + 1)
}
