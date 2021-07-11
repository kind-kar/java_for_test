package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
    private String address;
    private String email;
    private String email2;
    private String email3;

    @Column(name = "mobile")
    private String mobile;
    private String allEmails;

    @Column(name = "home")
    private String home;

    @Column(name = "work")
    private String work;

    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Column(name = "photo")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailTwo() {
        return email2;
    }

    public String getEmailThree() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGroup() { return group; }

    public int getId() { return id; }

    public String getHome() {
        return home;
    }

    public String getWork() {
        return work;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData  withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData  withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData  withEmailTwo(String email) {
        this.email2 = email;
        return this;
    }

    public ContactData  withEmailThree(String email) {
        this.email3 = email;
        return this;
    }
    public ContactData  withAllEmails(String emails) {
        this.allEmails = emails;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData  withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData  withGroup(String group) {
        this.group = group;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
