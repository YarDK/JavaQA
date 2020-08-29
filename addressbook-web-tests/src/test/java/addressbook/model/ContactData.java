package addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Expose
    @Column(name = "firstname")
    private String first_name = "";

    @Expose
    @Column(name = "lastname")
    private String last_name = "";

    @Column(name = "middlename")
    private String middle_name = "";

    @Column(name = "nickname")
    private String nick_name = "";

    @Column(name = "address")
    @Type(type = "text")
    private String address = "";

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String telephone_home = "";

    @Column(name = "mobile")
    @Type(type = "text")
    private String telephone_mobile = "";

    @Column(name = "work")
    @Type(type = "text")
    private String telephone_work = "";

    @Transient
    private String telephone_all = "";

    @Column(name = "email")
    @Type(type = "text")
    private String email_1 = "";

    @Column(name = "email2")
    @Type(type = "text")
    private String email_2 = "";

    @Column(name = "email3")
    @Type(type = "text")
    private String email_3 = "";

    @Transient
    private String email_all = "";

    @Transient
    private String group = "[none]";

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone_home() {
        return telephone_home;
    }

    public String getTelephone_mobile() {
        return telephone_mobile;
    }

    public String getTelephone_work() {
        return telephone_work;
    }

    public String getTelephone_all() {
        return telephone_all;
    }

    public String getEmail_1() {
        return email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public String getEmail_3() {
        return email_3;
    }

    public String getEmail_all() {
        return email_all;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        try {
            return new File(photo);
        } catch (NullPointerException e){
            System.out.println("\nWarning - Contact has not photo\n");
            return null;
        }
    }

    public ContactData withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", address='" + address + '\'' +
                ", telephone_home='" + telephone_home + '\'' +
                ", telephone_mobile='" + telephone_mobile + '\'' +
                ", telephone_work='" + telephone_work + '\'' +
                ", email_1='" + email_1 + '\'' +
                ", email_2='" + email_2 + '\'' +
                ", email_3='" + email_3 + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (middle_name != null ? !middle_name.equals(that.middle_name) : that.middle_name != null) return false;
        if (nick_name != null ? !nick_name.equals(that.nick_name) : that.nick_name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (telephone_home != null ? !telephone_home.equals(that.telephone_home) : that.telephone_home != null)
            return false;
        if (telephone_mobile != null ? !telephone_mobile.equals(that.telephone_mobile) : that.telephone_mobile != null)
            return false;
        if (telephone_work != null ? !telephone_work.equals(that.telephone_work) : that.telephone_work != null)
            return false;
        if (email_1 != null ? !email_1.equals(that.email_1) : that.email_1 != null) return false;
        if (email_2 != null ? !email_2.equals(that.email_2) : that.email_2 != null) return false;
        return email_3 != null ? email_3.equals(that.email_3) : that.email_3 == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (middle_name != null ? middle_name.hashCode() : 0);
        result = 31 * result + (nick_name != null ? nick_name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone_home != null ? telephone_home.hashCode() : 0);
        result = 31 * result + (telephone_mobile != null ? telephone_mobile.hashCode() : 0);
        result = 31 * result + (telephone_work != null ? telephone_work.hashCode() : 0);
        result = 31 * result + (email_1 != null ? email_1.hashCode() : 0);
        result = 31 * result + (email_2 != null ? email_2.hashCode() : 0);
        result = 31 * result + (email_3 != null ? email_3.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public ContactData withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public ContactData withMiddle_name(String middle_name) {
        this.middle_name = middle_name;
        return this;
    }

    public ContactData withNick_name(String nick_name) {
        this.nick_name = nick_name;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withTelephone_home(String telephone_home) {
        this.telephone_home = telephone_home;
        return this;
    }

    public ContactData withTelephone_mobile(String telephone_mobile) {
        this.telephone_mobile = telephone_mobile;
        return this;
    }

    public ContactData withTelephone_work(String telephone_work) {
        this.telephone_work = telephone_work;
        return this;
    }

    public ContactData withTelephone_all(String telephone_all) {
        this.telephone_all = telephone_all;
        return this;
    }

    public ContactData withEmail_1(String email_1) {
        this.email_1 = email_1;
        return this;
    }

    public ContactData withEmail_2(String email_2) {
        this.email_2 = email_2;
        return this;
    }

    public ContactData withEmail_3(String email_3) {
        this.email_3 = email_3;
        return this;
    }
    public ContactData withEmail_all(String email_all) {
        this.email_all = email_all;
        return this;
    }


    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

}
