//Refered to as PObj

class Person {
    String email;
    String name;
    String pw;

    public Person(String email, String name, String pw) {
        this.email = email;
        this.name = name;
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }
}