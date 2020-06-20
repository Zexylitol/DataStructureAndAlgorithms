package HashTable;

/**
 * @author yzze
 * @create 2020-05-18 20:45
 */
public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * java Object类中的hashCode是将对象地址映射成一个整形
     * @return
     */
    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();  // 不区分大小写
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Student another = (Student) obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
}
