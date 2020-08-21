package addressbook.model;

import java.io.File;

public class ContactData {
    private String first_name;
    private String last_name;
    private String middle_name;
    private String nick_name;
    private String address;
    private String telephone_home;
    private String telephone_mobile;
    private String telephone_work;
    private String telephone_all;
    private String email_1;
    private String email_2;
    private String email_3;
    private String email_all;
    private String group;
    private int id = Integer.MAX_VALUE;
    private File photo;


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
        return photo;
    }

    public ContactData withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
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
        this.photo = photo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", telephone_home='" + telephone_home + '\'' +
                ", group='" + group + '\'' +
                ", id=" + id +
                '}';
    }
}
