package com.example.DKHP_UIT.entities.ctdt;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.internal.build.AllowNonPortable;

import com.example.DKHP_UIT.entities.Subject;

import jakarta.mail.search.SubjectTerm;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CTDT")
public class CTDT {
    @EmbeddedId
    private CTDTID id;
    private String tenKhoa;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CTDT_Subject", joinColumns = {
            @JoinColumn(name = "CTDT_hocKy", referencedColumnName = "hocKy"),
            @JoinColumn(name = "CTDT_maKhoa", referencedColumnName = "maKhoa")
    }, inverseJoinColumns = @JoinColumn(name = "Subject_Id"))
    private Set<Subject> subjects = new HashSet<>();
}
