package com.example.DKHP_UIT.entities.OpenSubject;

import java.util.HashSet;
import java.util.Set;

import com.example.DKHP_UIT.entities.Subject;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "open_subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenSubject {
    @EmbeddedId
    private OpenSubjectId id;
}
