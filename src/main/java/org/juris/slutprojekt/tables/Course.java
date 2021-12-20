package org.juris.slutprojekt.tables;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private Education education;

    @Column(length = 60)
    private String name;

    public Course(String name) {
        this.name = name;
    }


    public Course() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", education=" + education +
                ", name='" + name + '\'' +
                '}';
    }
}