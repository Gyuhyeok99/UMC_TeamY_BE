package umc.teamY.project;

public enum ProjectTerm {

    TERM_24_1("24-1"),
    TERM_24_2("24-2"),
    TERM_25_1("25-1"),
    TERM_25_2("25-2");

    private String description;

    ProjectTerm(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
