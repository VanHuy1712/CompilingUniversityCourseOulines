/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "outline_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OutlineMethod.findAll", query = "SELECT o FROM OutlineMethod o"),
    @NamedQuery(name = "OutlineMethod.findById", query = "SELECT o FROM OutlineMethod o WHERE o.id = :id"),
    @NamedQuery(name = "OutlineMethod.findByWeight", query = "SELECT o FROM OutlineMethod o WHERE o.weight = :weight"),
    @NamedQuery(name = "OutlineMethod.findByName", query = "SELECT o FROM OutlineMethod o WHERE o.name = :name")})
public class OutlineMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    private Double weight;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "evaluation_method_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EvaluationMethod evaluationMethod;
    @JoinColumn(name = "outline_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Outline outline;

    public OutlineMethod() {
    }

    public OutlineMethod(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EvaluationMethod getEvaluationMethod() {
        return evaluationMethod;
    }

    public void setEvaluationMethod(EvaluationMethod evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public Outline getOutline() {
        return outline;
    }

    public void setOutline(Outline outline) {
        this.outline = outline;
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
        if (!(object instanceof OutlineMethod)) {
            return false;
        }
        OutlineMethod other = (OutlineMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vh.pojo.OutlineMethod[ id=" + id + " ]";
    }
    
}
