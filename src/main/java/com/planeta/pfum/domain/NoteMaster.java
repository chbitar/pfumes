package com.planeta.pfum.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.planeta.pfum.domain.enumeration.Semestre;

/**
 * A NoteMaster.
 */
@Entity
@Table(name = "note_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoteMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre")
    private Semestre semestre;

    @Column(name = "note_cc_1")
    private Double noteCC1;

    @Column(name = "note_cc_2")
    private Double noteCC2;

    @Column(name = "note_final")
    private Double noteFinal;

    @Column(name = "date")
    private Instant date;

    @ManyToOne
    @JsonIgnoreProperties("noteMasters")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("noteMasters")
    private Module module;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public NoteMaster semestre(Semestre semestre) {
        this.semestre = semestre;
        return this;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Double getNoteCC1() {
        return noteCC1;
    }

    public NoteMaster noteCC1(Double noteCC1) {
        this.noteCC1 = noteCC1;
        return this;
    }

    public void setNoteCC1(Double noteCC1) {
        this.noteCC1 = noteCC1;
    }

    public Double getNoteCC2() {
        return noteCC2;
    }

    public NoteMaster noteCC2(Double noteCC2) {
        this.noteCC2 = noteCC2;
        return this;
    }

    public void setNoteCC2(Double noteCC2) {
        this.noteCC2 = noteCC2;
    }

    public Double getNoteFinal() {
        return noteFinal;
    }

    public NoteMaster noteFinal(Double noteFinal) {
        this.noteFinal = noteFinal;
        return this;
    }

    public void setNoteFinal(Double noteFinal) {
        this.noteFinal = noteFinal;
    }

    public Instant getDate() {
        return date;
    }

    public NoteMaster date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public NoteMaster user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Module getModule() {
        return module;
    }

    public NoteMaster module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteMaster)) {
            return false;
        }
        return id != null && id.equals(((NoteMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NoteMaster{" +
            "id=" + getId() +
            ", semestre='" + getSemestre() + "'" +
            ", noteCC1=" + getNoteCC1() +
            ", noteCC2=" + getNoteCC2() +
            ", noteFinal=" + getNoteFinal() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
