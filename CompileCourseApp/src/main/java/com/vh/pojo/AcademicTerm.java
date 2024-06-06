/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "academic_term")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicTerm.findAll", query = "SELECT a FROM AcademicTerm a"),
    @NamedQuery(name = "AcademicTerm.findById", query = "SELECT a FROM AcademicTerm a WHERE a.id = :id"),
    @NamedQuery(name = "AcademicTerm.findByYearTearm", query = "SELECT a FROM AcademicTerm a WHERE a.yearTearm = :yearTearm")})
public class AcademicTerm implements Serializable {

    @OneToMany(mappedBy = "academicId")
    private Collection<OutlineTerm> outlineTermCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "year_tearm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearTearm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicTermId")
    private Set<Outline> outlineSet;

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

    public Date getYearTearm() {
        return yearTearm;
    }

    public void setYearTearm(Date yearTearm) {
        this.yearTearm = yearTearm;
    }

    @XmlTransient
    public Set<Outline> getOutlineSet() {
        return outlineSet;
    }

    public void setOutlineSet(Set<Outline> outlineSet) {
        this.outlineSet = outlineSet;
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

    @XmlTransient
    public Collection<OutlineTerm> getOutlineTermCollection() {
        return outlineTermCollection;
    }

    public void setOutlineTermCollection(Collection<OutlineTerm> outlineTermCollection) {
        this.outlineTermCollection = outlineTermCollection;
    }
    
}
