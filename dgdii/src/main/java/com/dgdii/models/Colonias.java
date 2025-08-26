package com.dgdii.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "COLONIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colonias.findAll", query = "SELECT c FROM Colonias c"),
    @NamedQuery(name = "Colonias.findByIdColonia", query = "SELECT c FROM Colonias c WHERE c.idColonia = :idColonia"),
    @NamedQuery(name = "Colonias.findByCp", query = "SELECT c FROM Colonias c WHERE c.cp = :cp"),
    @NamedQuery(name = "Colonias.findByColonia", query = "SELECT c FROM Colonias c WHERE c.colonia = :colonia")})
public class Colonias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_COLONIAS")
    @SequenceGenerator(name = "SEC_COLONIAS", sequenceName = "SEC_COLONIAS", allocationSize = 1)
    @Column(name = "ID_COLONIA")
    private Integer idColonia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CP")
    private String cp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COLONIA")
    private String colonia;
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Municipios idMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColonia", fetch = FetchType.LAZY)
    private List<Personas> personasList;

    public Colonias() {
    }

    public Colonias(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public Colonias(Integer idColonia, String cp, String colonia) {
        this.idColonia = idColonia;
        this.cp = cp;
        this.colonia = colonia;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColonia != null ? idColonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colonias)) {
            return false;
        }
        Colonias other = (Colonias) object;
        if ((this.idColonia == null && other.idColonia != null) || (this.idColonia != null && !this.idColonia.equals(other.idColonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  this.colonia + "(" + this.cp+")";
    }
    
}
