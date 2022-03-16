package mainPackage

import groovy.json.JsonSlurper

import javax.swing.JOptionPane

//READ Files
def jsonSlurper = new JsonSlurper()
File file = new File("candidates.json")
List candidates = jsonSlurper.parse(file).collect {
    new FisicPerson(name: it.name, email: it.email, cpf: it.cpf, skills: it.skills, country: it.country, state: it.state, description: it.description)
}

file = new File("companies.json")
List companies = jsonSlurper.parse(file).collect {
    new JuridicPerson(name: it.name, email: it.email, cnpj: it.cnpj, necessarySkills: it.necessarySkills, country: it.country, state: it.state, cep: it.cep, description: it.description)
}


def input = 1
while (input != 0){
    input = JOptionPane.&showInputDialog "BEM VINDO AO LINKETINDER!\n" +
            "O que você deseja listar?\n" +
            "Aperte 1 para listar os candidatos\n" +
            "Aperte 2 para listar as empresas\n" +
            "Aperte 0 para sair\n"

    if(input == "1"){
        def buffer = "----- candidatos -------\n"
        for (person in candidates){
            buffer= buffer + person.toString() + "\n"
        }
        JOptionPane.showMessageDialog(null,buffer)
    }
    if(input == "2"){
        def buffer = "----- empresas -------\n"
        for (companie in companies){
            buffer = buffer + companie.toString() + "\n"
        }
        JOptionPane.showMessageDialog(null, buffer)
    }
    if(input == "0"){
        break
    }
    if(input != 1 && input != 2){
        JOptionPane.showMessageDialog(null, "INSIRA UMA OPÇÃO VÁLIDA!")
    }
}

//Update Files
def jsonCandidates = groovy.json.JsonOutput.toJson(candidates)
new File("candidates.json").write(jsonCandidates.toString())

def jsonCompanies = groovy.json.JsonOutput.toJson(companies)
new File("companies.json").write(jsonCompanies.toString())