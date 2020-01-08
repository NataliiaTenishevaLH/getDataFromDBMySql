public class Student {
    private int id;
    private String fioStudent;

    public Student(int id, String fioStudent){
        this.id = id;
        this.fioStudent = fioStudent;
    }

    @Override
    public String toString(){
        return this.fioStudent;
    }

    public int getId(){
        return this.id;
    }
}
