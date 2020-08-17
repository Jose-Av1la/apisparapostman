/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jgasd
 */
@Entity
@Table(name = "padre_familia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PadreFamilia.findAll", query = "SELECT p FROM PadreFamilia p")
    , @NamedQuery(name = "PadreFamilia.findByCedula", query = "SELECT p FROM PadreFamilia p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "PadreFamilia.findByNombre", query = "SELECT p FROM PadreFamilia p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PadreFamilia.findByApellido", query = "SELECT p FROM PadreFamilia p WHERE p.apellido = :apellido")
    , @NamedQuery(name = "PadreFamilia.findByDireccion", query = "SELECT p FROM PadreFamilia p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "PadreFamilia.findByTelefono", query = "SELECT p FROM PadreFamilia p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "PadreFamilia.findByCorreo", query = "SELECT p FROM PadreFamilia p WHERE p.correo = :correo")})
public class PadreFamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;

    public PadreFamilia() {
    }

    public PadreFamilia(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PadreFamilia)) {
            return false;
        }
        PadreFamilia other = (PadreFamilia) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PadreFamilia[ cedula=" + cedula + " ]";
    }
    
}
