/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "academic_term")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicTerm.findAll", query = "SELECT a FROM AcademicTerm a"),
    @NamedQuery(name = "AcademicTerm.findById", query = "SELECT a FROM AcademicTerm a WHERE a.id = :id"),
    @NamedQuery(name = "AcademicTerm.findByName", query = "SELECT a FROM AcademicTerm a WHERE a.name = :name")})
public class AcademicTerm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "academicId")
    private Set<OutlineTerm> outlineTermSet;

    public AcademicTerm() {
    }

    public AcademicTerm(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<OutlineTerm> getOutlineTermSet() {
        return outlineTermSet;
    }

    public void setOutlineTermSet(Set<OutlineTerm> outlineTermSet) {
        this.outlineTermSet = outlineTermSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcademicTerm)) {
            return false;
        }
        AcademicTerm other = (AcademicTerm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vh.pojo.AcademicTerm[ id=" + id + " ]";
    }
    
}
