package mainPackage

import groovy.transform.ToString

@ToString(includePackage = false, includeNames = true)
class Person {
    String name
    String email
    String country
    String state
    String description
}

class JuridicPerson extends Person{
    String cnpj
    String cep
    List necessarySkills= []

    @Override
    String toString() {
        return "name:" + name + ", cnpj:" + cnpj + ", necessarySkills:" + necessarySkills + ", email:" + email + ", country:" + country + ", state:" + state + ",cep:" + cep + ", description:" + description
    }
}

//@ToString(includeSuper = true, includeNames = true, includePackage = false, leftDelimiter = "", rightDelimiter = "")
class FisicPerson extends Person{
    String cpf
    List skills = []

    @Override
    String toString() {
        return "name:" + name + ", cpf:" + cpf + ", skills:" + skills + ", email:" + email + ", country:" + country + ", state:" + state + ", description:" + description
    }
}