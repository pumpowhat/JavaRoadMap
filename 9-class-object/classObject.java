// Constructor
class Student {
    private String name;
    private int age;
    private String studentID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}

public class classObject {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Nguyen Van An");
        s1.setAge(20);
        s1.setStudentID("SV001");

        Student s2 = new Student();
        s2.setName("Tran Thi Binh");
        s2.setAge(21);
        s2.setStudentID("SV002");

        System.out.println("Sinh vien 1:");
        System.out.println("Ten: " + s1.getName());
        System.out.println("Tuoi: " + s1.getAge());
        System.out.println("Ma sinh vien: " + s1.getStudentID());

        System.out.println("\nSinh vien 2:");
        System.out.println("Ten: " + s2.getName());
        System.out.println("Tuoi: " + s2.getAge());
        System.out.println("Ma sinh vien: " + s2.getStudentID());
    }
}
