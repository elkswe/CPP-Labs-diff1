package university.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Student extends SomeStudent {
    protected static final double CHANCE_TO_ATTEND = 0.25;

    protected int group;
    protected boolean attendClass = false;
    protected List<Mark> marks = new LinkedList<>();

    public Student() {
        this(650503);
    }

    public Student(int group) {
        super();
        this.group = group;
    }

    public Student(int group, String secondName, String firstName, String middleName) {
        super(secondName, firstName, middleName);
        this.group = group;
    }

    public void showMarks() {
        marks.sort(Comparator.comparing(o -> o.subject));
        System.out.println(this.toString());
        for (Mark mark :
                marks) {
            System.out.print('\t');
            System.out.println(mark.toString());
        }
    }

    public void attendOrNot() {
        attendClass = CHANCE_TO_ATTEND < Math.random();
    }

    public void getKnowledge(String subject) {
        boolean flag = false;
        int startedMark = 1;
        for (Mark mark :
                marks) {
            if (mark.subject.equals(subject)) {
                ++mark.mark;
                flag = true;
            }
        }
        if (!flag) {
            marks.add(new Mark(subject, startedMark));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return group == student.group &&
                attendClass == student.attendClass &&
                Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), group, attendClass, marks);
    }

    @Override
    public String toString() {
        return Integer.toString(group) + ' ' + super.toString();
    }

    public int getGroup() {
        return group;
    }

    public boolean isAttendClass() {
        return attendClass;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public class Mark {
        String subject;
        int mark;

        public Mark() {
            subject = null;
            mark = 0;
        }

        public Mark(String subject, int mark) {
            this.subject = subject;
            this.mark = mark;
        }

        public void clear() {
            subject = null;
            mark = 0;
        }

        @Override
        public String toString() {
            return subject + ' ' + Integer.toString(mark);
        }
    }
}
