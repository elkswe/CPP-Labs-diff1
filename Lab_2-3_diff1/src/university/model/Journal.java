package university.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/** . */
public class Journal {
    /** . */
    private List<JournalLine> studentsPresence;

    /** . */
    public Journal() {
        studentsPresence = new LinkedList<>();
    }

    /**
     * @param student
     */
    public void markStudent(Student student, boolean key) {
        JournalLine journalLine = null;
        for (JournalLine line
                : studentsPresence) {
            if (line.compareTo(student) == 0) {
                journalLine = line;
                if (student.isAttendClass() || !key) journalLine.markPresence();
                else journalLine.markMissing();
                break;
            }
        }
        if (journalLine == null) {
            journalLine = new JournalLine(student.toString(),
                    student.isAttendClass());
            studentsPresence.add(journalLine);
        }
    }

    public void remarkStudent(Student student) {
        for (JournalLine line
                : studentsPresence) {
            if (line.compareTo(student) == 0) {
                line.presence.set(
                        line.presence.size() - 1,
                        false);
            }
        }
    }

    //For debug
    public void showJournal() {
        System.out.println("Журнал:\n" +
                "Студент::Присутствие на паре");
        for (JournalLine line
                : studentsPresence) {

            System.out.printf("%-50s", line.student);
            for (boolean value
                    : line.presence) {
                System.out.printf(" %c", value ? '+' : '-');
            }
            System.out.println();
        }
        System.out.println("\n===========================================\n");
    }

    public List<JournalLine> getStudentsPresence() {
        return studentsPresence;
    }

    /** . */
    public class JournalLine implements Comparable {
        /** . */
        public String student;
        /** . */
        public List<Boolean> presence;

        /**
         * @param student
         * @param presence
         */
        public JournalLine(String student, List<Boolean> presence) {
            this.student = student;
            this.presence = presence;
        }

        /**
         * @param student
         */
        public JournalLine(final String student) {
            this(student, new LinkedList<>());
        }

        /**
         * @param student
         * @param presence
         */
        public JournalLine(final String student, final boolean presence) {
            this(student);
            this.presence.add(presence);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (boolean value
                    : presence) {
                sb.append(value ? '+' : '-')
                        .append(' ');
            }
            System.out.println(sb.toString());
            return sb.toString();
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof SomeStudent))
                return 1;
            Student student = (Student) o;
            return this.student.compareTo(student.toString());
        }

        /** . */
        public void markPresence() {
            presence.add(true);
        }

        /** . */
        public void markMissing() {
            presence.add(false);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JournalLine that = (JournalLine) o;
            return Objects.equals(student, that.student) &&
                    Objects.equals(presence, that.presence);
        }

        @Override
        public int hashCode() {

            return Objects.hash(student, presence);
        }
    }
}