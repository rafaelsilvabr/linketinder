package mainPackage

import org.junit.jupiter.api.Test;

class linketinderTests {

    @Test
    void testeCriarUser(){
        List listofUsers = []

        listofUsers.add(new FisicPerson(nome: "Joao", email: "joaozinho123@mail.com", cpf: "123456789", skills: ["java", "groovy"], pais: "Brazil", estado: "RJ", descricao: "Um programador cansado"))
        assert ("[nome:Joao, cpf:123456789, skills:[java, groovy], email:joaozinho123@mail.com, pais:Brazil, estado:RJ, descricao:Um programador cansado]" == listofUsers.toString())
    }

    @Test
    void testeCriarEmpresa(){
        List listofCompanies = []

        listofCompanies.add(new JuridicPerson(nome: "DevsEspetaculares", email: "devsdevs@mail.com", cnpj: "8978123216513", necessarySkills: ["java", "spring"], pais: "Brazil", estado: "SP", descricao: "Uma empresa sensacional"))
        assert ("[nome:DevsEspetaculares, cnpj:8978123216513, necessarySkills:[java, spring], email:devsdevs@mail.com, pais:Brazil, estado:SP,cep:null, descricao:Uma empresa sensacional]" == listofCompanies.toString())
    }
}
