package com.dgdii.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "MATERIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materias.findAll", query = "SELECT m FROM Materias m"),
    @NamedQuery(name = "Materias.findByIdMateria", query = "SELECT m FROM Materias m WHERE m.idMateria = :idMateria"),
    @NamedQuery(name = "Materias.findByMateria", query = "SELECT m FROM Materias m WHERE m.materia = :materia")})
public class Materias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_MATERIAS")
    @SequenceGenerator(name = "SEC_MATERIAS", sequenceName = "SEC_MATERIAS", allocationSize = 1)
    @Column(name = "ID_MATERIA")
    private Integer idMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MATERIA")
    private String materia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria", fetch = FetchType.LAZY)
    private List<AlumnosMaterias> alumnosMateriasList;
    @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID_PROFESOR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesores idProfesor;

    public Materias() {
    }

    public Materias(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Materias(Integer idMateria, String materia) {
        this.idMateria = idMateria;
        this.materia = materia;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @XmlTransient
    public List<AlumnosMaterias> getAlumnosMateriasList() {
        return alumnosMateriasList;
    }

    public void setAlumnosMateriasList(List<AlumnosMaterias> alumnosMateriasList) {
        this.alumnosMateriasList = alumnosMateriasList;
    }

    public Profesores getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesores idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materias)) {
            return false;
        }
        Materias other = (Materias) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.materia;
    }
    
}
