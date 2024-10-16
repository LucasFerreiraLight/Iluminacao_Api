package br.com.Iluminacao.model;

public enum UsuarioRole {

    ADMIN("admin"),
    USER("user"),

    MANUTENCAO("manut");


    private String role;

        UsuarioRole(String role){
            this.role = role;
        }

        // Metodo GET, poderia usar o Lombok, mas da pra fazer assim.
        public String getRole() {
            return role;
        }

}
