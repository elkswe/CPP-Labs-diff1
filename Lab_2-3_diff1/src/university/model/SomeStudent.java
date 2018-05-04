package university.model;

import java.util.Objects;

public abstract class SomeStudent {
    private String secondName;
    private String firstName;
    private String middleName;


    public SomeStudent() {
        this("Ананько", "Егор", "Эдуардович");
    }

    public SomeStudent(String secondName, String firstName, String middleName) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeStudent that = (SomeStudent) o;
        return Objects.equals(secondName, that.secondName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(secondName, firstName, middleName);
    }

    @Override
    public String toString() {
        return secondName + ' ' + firstName + ' ' + middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
