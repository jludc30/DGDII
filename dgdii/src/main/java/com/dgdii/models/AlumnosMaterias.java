package com.dgdii.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ALUMNOS_MATERIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnosMaterias.findAll", query = "SELECT a FROM AlumnosMaterias a"),
    @NamedQuery(name = "AlumnosMaterias.findByIdAlumnoMateria", query = "SELECT a FROM AlumnosMaterias a WHERE a.idAlumnoMateria = :idAlumnoMateria")})
public class AlumnosMaterias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ALUMNOS_MATERIAS")
    @SequenceGenerator(name = "SEC_ALUMNOS_MATERIAS", sequenceName = "SEC_ALUMNOS_MATERIAS", allocationSize = 1)
    @Column(name = "ID_ALUMNO_MATERIA")
    private Integer idAlumnoMateria;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alumnos idAlumno;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materias idMateria;

    public AlumnosMaterias() {
    }

    public AlumnosMaterias(Integer idAlumnoMateria) {
        this.idAlumnoMateria = idAlumnoMateria;
    }

    public Integer getIdAlumnoMateria() {
        return idAlumnoMateria;
    }

    public void setIdAlumnoMateria(Integer idAlumnoMateria) {
        this.idAlumnoMateria = idAlumnoMateria;
    }

    public Alumnos getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumnos idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materias getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materias idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumnoMateria != null ? idAlumnoMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnosMaterias)) {
            return false;
        }
        AlumnosMaterias other = (AlumnosMaterias) object;
        if ((this.idAlumnoMateria == null && other.idAlumnoMateria != null) || (this.idAlumnoMateria != null && !this.idAlumnoMateria.equals(other.idAlumnoMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dgdii.models.AlumnosMaterias[ idAlumnoMateria=" + idAlumnoMateria + " ]";
    }
    
}
