package addressbook.model;

public class ContactData {
    private String first_name;
    private String last_name;
    private String middle_name;
    private String nick_name;
    private String telephone_home;
    private String group;
    private int id = Integer.MAX_VALUE;


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

    public String getTelephone_home() {
        return telephone_home;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
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

    public ContactData withTelephone_home(String telephone_home) {
        this.telephone_home = telephone_home;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
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
