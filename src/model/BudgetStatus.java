package model;

public enum BudgetStatus {
    REPROVADO("Reprovado"),
    APROVADO("Aprovado"),
    EM_ESPERA("Em espera");

    private String term;

    private BudgetStatus(String term){
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
        terms[0] = BudgetStatus.REPROVADO.getTermo();
        terms[1] = BudgetStatus.APROVADO.getTermo();
        terms[2] = BudgetStatus.EM_ESPERA.getTermo();

        return terms;
    }

    public static Integer getEnumIntValue(Object term) {
        int value;
        if ("Reprovado".equals(term)) {
            value = 0;
        } else if ("Aprovado".equals(term)) {
            value = 1;
        }else if ("Em espera".equals(term)) {
            value = 2;
        }else {
            value = 2;
        }
        return value;
    }

    public static String getEnumByValue(int value) {
        if (value == 0) {
            return "Reprovado";
        } else if (value == 1) {
            return "Aprovado";
        } else if (value == 2) {
            return "Em espera";
        } else {
            return "Em espera";
        }
    }
}
