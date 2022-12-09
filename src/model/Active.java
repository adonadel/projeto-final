package model;

public enum Active {
    DESATIVADO("Desativado"),
    ATIVADO("Ativado");

    private String term;

    private Active(String term){
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
        terms[0] = Active.DESATIVADO.getTermo();
        terms[1] = Active.ATIVADO.getTermo();

        return terms;
    }

    public static Integer getEnumIntValue(Object term) {
        int value;
        if ("Desativado".equals(term)) {
            value = 0;
        }else {
            value = 1;
        }
        return value;
    }

    public static String getEnumByValue(int value) {
        if (value == 0) {
            return "Desativado";
        } else {
            return "Ativado";
        }
    }
}
