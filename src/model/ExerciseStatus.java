package model;

public enum ExerciseStatus {
    PASSADO("Passado"),
    EXERCICIO("Exercício"),
    PLANEJADO("Planejado");

    private String term;

    private ExerciseStatus(String term){
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
        String[] terms = new String[3];
        terms[0] = ExerciseStatus.PASSADO.getTermo();
        terms[1] = ExerciseStatus.EXERCICIO.getTermo();
        terms[2] = ExerciseStatus.PLANEJADO.getTermo();

        return terms;
    }

    public static Integer getEnumIntValue(Object term) {
        int value;
        if ("Passado".equals(term)) {
            value = 0;
        } else if ("Exercício".equals(term)) {
            value = 1;
        } else if ("Planejado".equals(term)) {
            value = 2;
        } else {
            value = 2;
        }
        return value;
    }

    public static String getEnumByValue(int value) {
        if (value == 0) {
            return "Passado";
        } else if (value == 1) {
            return "Exercício";
        } else if (value == 2) {
            return "Planejado";
        } else {
            return "Planejado";
        }
    }
}
