/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "evaluation_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluationMethod.findAll", query = "SELECT e FROM EvaluationMethod e"),
    @NamedQuery(name = "EvaluationMethod.findById", query = "SELECT e FROM EvaluationMethod e WHERE e.id = :id"),
    @NamedQuery(name = "EvaluationMethod.findByName", query = "SELECT e FROM EvaluationMethod e WHERE e.name = :name")})
public class EvaluationMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationMethod")
    @JsonIgnore
    private Set<OutlineMethod> outlineMethodSet;

    public EvaluationMethod() {
    }

    public EvaluationMethod(Integer id) {
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
    public Set<OutlineMethod> getOutlineMethodSet() {
        return outlineMethodSet;
    }

    public void setOutlineMethodSet(Set<OutlineMethod> outlineMethodSet) {
        this.outlineMethodSet = outlineMethodSet;
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
        if (!(object instanceof EvaluationMethod)) {
            return false;
        }
        EvaluationMethod other = (EvaluationMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vh.pojo.EvaluationMethod[ id=" + id + " ]";
    }
    
}
