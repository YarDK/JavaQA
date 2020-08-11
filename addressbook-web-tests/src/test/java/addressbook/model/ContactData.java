package addressbook.model;

public class ContactData {
    private final String first_name;
    private final String last_name;
    private final String middle_name;
    private final String nick_name;
    private final String telephone_home;
    private final String group;
    private int id;


    public ContactData(
            String first_name,
            String last_name,
            String middle_name,
            String nick_name,
            String telephone_home,
            String group
    ) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.nick_name = nick_name;
        this.telephone_home = telephone_home;
        this.group = group;
        this.id = Integer.MAX_VALUE;
    }

    public ContactData(
            String first_name,
            String last_name,
            int id
    ) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = "[none]";
        this.nick_name = "[none]";
        this.telephone_home = "[none]";
        this.group = "[none]";
        this.id = id;
    }

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
