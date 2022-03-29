package mainPackage

import org.junit.jupiter.api.Test;

class linketinderTests {

    @Test
    void testeCriarUser(){
        List listofUsers = []

        listofUsers.add(new FisicPerson(name: "Joao", email: "joaozinho123@mail.com", cpf: "123456789", skills: ["java", "groovy"], country: "Brazil", state: "RJ", description: "Um programador cansado"))
        assert ("[name:Joao, cpf:123456789, skills:[java, groovy], email:joaozinho123@mail.com, country:Brazil, state:RJ, description:Um programador cansado]" == listofUsers.toString())
    }

    @Test
    void testeCriarEmpresa(){
        List listofCompanies = []

        listofCompanies.add(new JuridicPerson(name: "DevsEspetaculares", email: "devsdevs@mail.com", cnpj: "8978123216513", necessarySkills: ["java", "spring"], country: "Brazil", state: "SP", description: "Uma empresa sensacional"))
        assert ("[name:DevsEspetaculares, cnpj:8978123216513, necessarySkills:[java, spring], email:devsdevs@mail.com, country:Brazil, state:SP,cep:null, description:Uma empresa sensacional]" == listofCompanies.toString())
    }
}
