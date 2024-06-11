/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "outline_term")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OutlineTerm.findAll", query = "SELECT o FROM OutlineTerm o"),
    @NamedQuery(name = "OutlineTerm.findById", query = "SELECT o FROM OutlineTerm o WHERE o.id = :id"),
    @NamedQuery(name = "OutlineTerm.findByCreatedDate", query = "SELECT o FROM OutlineTerm o WHERE o.createdDate = :createdDate")})
public class OutlineTerm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "academic_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicTerm academicId;
    @JoinColumn(name = "outline_id", referencedColumnName = "id")
    @ManyToOne
    private Outline outlineId;

    public OutlineTerm() {
    }

    public OutlineTerm(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public AcademicTerm getAcademicId() {
        return academicId;
    }

    public void setAcademicId(AcademicTerm academicId) {
        this.academicId = academicId;
    }

    public Outline getOutlineId() {
        return outlineId;
    }

    public void setOutlineId(Outline outlineId) {
        this.outlineId = outlineId;
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
        if (!(object instanceof OutlineTerm)) {
            return false;
        }
        OutlineTerm other = (OutlineTerm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vh.pojo.OutlineTerm[ id=" + id + " ]";
    }
    
}
