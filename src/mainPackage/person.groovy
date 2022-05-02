package mainPackage

import groovy.transform.ToString

@ToString(includePackage = false, includeNames = true)
class Person {
    String nome
    String email
    String pais
    String estado
    String descricao
    String senha
    String cep

}

class JuridicPerson extends Person{
    String cnpj
    List<Vaga> vagas=[]

    @Override
    public String toString() {
        return "JuridicPerson{" +
                "cnpj='" + cnpj + '\'' +
                ", vagas=" + vagas +
                '}';
    }
}

//@ToString(includeSuper = true, includeNames = true, includePackage = false, leftDelimiter = "", rightDelimiter = "")
class FisicPerson extends Person{
    String sobrenome
    String cpf
    List skills = []
    String dataNascimento

    @Override
    public String toString() {
        return "FisicPerson{" +
                "sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", skills=" + skills +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }
}

class Vaga{
    String nome
    String descricao
    List<String> competencias = []

    @Override
    public String toString() {
        return "Vaga{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", competencias=" + competencias +
                '}';
    }
}