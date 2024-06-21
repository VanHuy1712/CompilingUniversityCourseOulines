/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "outline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outline.findAll", query = "SELECT o FROM Outline o"),
    @NamedQuery(name = "Outline.findById", query = "SELECT o FROM Outline o WHERE o.id = :id"),
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
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @NotBlank(message = "{outline.language.notBlank}")
    @Size(max = 45)
    @Column(name = "language")
    private String language;
    @NotBlank(message = "{outline.teachingMethod.notBlank}")
    @Size(max = 45)
    @Column(name = "teching_method")
    private String techingMethod;
    @NotBlank(message = "{outline.knowledge.notBlank}")
    @Size(max = 45)
    @Column(name = "knowledge")
    private String knowledge;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull(message = "{outline.credit.nullErr}")
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
    @NotNull(message = "{outline.course.nullErr}")
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @JsonIgnore
    private Course course;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @JsonIgnore
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outline")
    @JsonIgnore
    private Set<OutlineMethod> outlineMethodSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outline")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outlineId")
    @JsonIgnore
    private Set<OutlineTerm> outlineTermSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
//    @JsonIgnore
//    private Set<ProdTag> prodTagSet;

    @Transient
    private MultipartFile file;

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

    @XmlTransient
    public Set<OutlineTerm> getOutlineTermSet() {
        return outlineTermSet;
    }

    public void setOrderDetailSet(Set<OutlineTerm> outlineTermSet) {
        this.outlineTermSet = outlineTermSet;
    }

//    @XmlTransient
//    public Set<AcademicTerm> getAcademicTermSet() {
//        return academicTermSet;
//    }
//
//    public void setAcademicTermSet(Set<AcademicTerm> academicTermSet) {
//        this.academicTermSet = academicTermSet;
//    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Set<OutlineMethod> getOutlineMethodSet() {
        return outlineMethodSet;
    }

    public void setOutlineMethodSet(Set<OutlineMethod> outlineMethodSet) {
        this.outlineMethodSet = outlineMethodSet;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
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

    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
