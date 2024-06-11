/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "outline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outline.findAll", query = "SELECT o FROM Outline o"),
    @NamedQuery(name = "Outline.findById", query = "SELECT o FROM Outline o WHERE o.id = :id"),
    @NamedQuery(name = "Outline.findByTitle", query = "SELECT o FROM Outline o WHERE o.title = :title"),
    @NamedQuery(name = "Outline.findByCreateDate", query = "SELECT o FROM Outline o WHERE o.createDate = :createDate"),
    @NamedQuery(name = "Outline.findByLanguage", query = "SELECT o FROM Outline o WHERE o.language = :language"),
    @NamedQuery(name = "Outline.findByTechingMethod", query = "SELECT o FROM Outline o WHERE o.techingMethod = :techingMethod"),
    @NamedQuery(name = "Outline.findByKnowledge", query = "SELECT o FROM Outline o WHERE o.knowledge = :knowledge"),
    @NamedQuery(name = "Outline.findByCredit", query = "SELECT o FROM Outline o WHERE o.credit = :credit")})
public class Outline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 45)
    @Column(name = "language")
    private String language;
    @Size(max = 45)
    @Column(name = "teching_method")
    private String techingMethod;
    @Size(max = 45)
    @Column(name = "knowledge")
    private String knowledge;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "credit")
    private Float credit;
    @Lob
    @Size(max = 65535)
    @Column(name = "policy")
    private String policy;
    @Lob
    @Size(max = 65535)
    @Column(name = "objectives")
    private String objectives;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outlineId")
    private Set<Comment> commentSet;
    @OneToMany(mappedBy = "outlineId")
    private Set<OutlineTerm> outlineTermSet;

    public Outline() {
    }

    public Outline(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTechingMethod() {
        return techingMethod;
    }

    public void setTechingMethod(String techingMethod) {
        this.techingMethod = techingMethod;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Outline)) {
            return false;
        }
        Outline other = (Outline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vh.pojo.Outline[ id=" + id + " ]";
    }
    
}
