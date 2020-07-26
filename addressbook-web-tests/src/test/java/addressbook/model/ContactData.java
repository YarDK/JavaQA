package addressbook.model;

public class ContactData {
    private final String first_name;
    private final String last_name;
    private final String middle_name;
    private final String nick_name;
    private final String telephone_home;

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



    public ContactData(
            String first_name,
            String last_name,
            String middle_name,
            String nick_name,
            String telephone_home
    ) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.nick_name = nick_name;
        this.telephone_home = telephone_home;
    }


}
