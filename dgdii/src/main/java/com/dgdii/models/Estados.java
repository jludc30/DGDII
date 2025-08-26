package com.dgdii.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ESTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e"),
    @NamedQuery(name = "Estados.findByIdEstado", query = "SELECT e FROM Estados e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estados.findByEstado", query = "SELECT e FROM Estados e WHERE e.estado = :estado"),
    @NamedQuery(name = "Estados.findByClave", query = "SELECT e FROM Estados e WHERE e.clave = :clave")})
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ESTADOS")
    @SequenceGenerator(name = "SEC_ESTADOS", sequenceName = "SEC_ESTADOS", allocationSize = 1)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLAVE")
    private String clave;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paises idPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Municipios> municipiosList;

    public Estados() {
    }

    public Estados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados(Integer idEstado, String estado, String clave) {
        this.idEstado = idEstado;
        this.estado = estado;
        this.clave = clave;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.estado+" ("+this.clave+")";
    }
    
}
