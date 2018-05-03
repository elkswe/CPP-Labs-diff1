package University.model;

import java.util.Objects;

public abstract class SomeClass {
    protected String subject;
    protected Teacher teacher;

    SomeClass() {
        this(new Teacher("Teacher-test", "Teacher-test", "Teacher-test", "Teacher-test"));
    }

    SomeClass(Teacher teacher) {
        this.subject = teacher.getSubject();
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeClass someClass = (SomeClass) o;
        return Objects.equals(subject, someClass.subject) &&
                Objects.equals(teacher, someClass.teacher);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, teacher);
    }

    @Override
    public String toString() {
        return teacher.toString();
    }
}
