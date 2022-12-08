package model;

public enum UserType {
    GERENTE("Gerente"),
    FUNCIONARIO("Funcionario");

    private String term;

    private UserType(String term){
        this.term = term;
    }

    public String getTermo() {
        return term;
    }

    @Override
    public String toString() {
        return this.getTermo();
    }

    public static Object[] getEnumArray() {
        String[] terms = new String[2];
        terms[0] = UserType.GERENTE.getTermo();
        terms[1] = UserType.FUNCIONARIO.getTermo();

        return terms;
    }

    public static Integer getEnumIntValue(Object term) {
        int value;
        if ("Gerente".equals(term)) {
            value = 0;
        } else {
            value = 1;
        }
        return value;
    }
}
