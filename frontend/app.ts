const a: string = "Teste3"
console.log(a)

class Person{
    name: string
    email: string
    country: string
    state: string
    description: string

    constructor(name: string, email: string, country: string, state: string, description: string) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.state = state;
        this.description = description;
    }
}

class JuridicPerson extends Person{
    cnpj: string
    cep: string
    necessarySkils: string[]

    constructor(name: string, email: string, country: string, state: string, description: string, cnpj: string, cep: string, necessarySkils: string[]) {
        super(name, email, country, state, description);
        this.cnpj = cnpj;
        this.cep = cep;
        this.necessarySkils = necessarySkils;
    }
}

class FisicPerson extends Person{
    cpf: string
    skills: string[]
    telefone: string

    constructor(name: string, email: string, country: string, state: string, description: string, cpf: string, skills: string[], telefone:string) {
        super(name, email, country, state, description);
        this.cpf = cpf;
        this.skills = skills;
        this.telefone = telefone;
    }
}

let empresas: Array<JuridicPerson> = new Array<JuridicPerson>()
let candidatos: Array<FisicPerson> = new Array<FisicPerson>()

const candidato1 = new FisicPerson("Joao", "joaozinho@gmail.com", "Brasil", "GO", "Um programador iniciante", "123456789564", ["java", "groovy"])
const candidato2 = new FisicPerson("Fernando", "fernandinho@gmail.com", "Brasil", "SP", "Um programador veterano", "78988456821", ["angular", "javascript"])
candidatos.push(candidato1)
candidatos.push(candidato2)

const empresa1 = new JuridicPerson("Empresa01", "company@mail.com", "Brasil", "GO", "Uma Empresa legal")
const empresa2 = new JuridicPerson("Empresa02", "company2@mail.com", "Brasil", "RJ", "Uma Empresa chata")
empresas.push(empresa1)
empresas.push(empresa2)

const inputName = document.querySelector(".inputName")
const inputCPF = document.querySelector(".inputCPF")
const inputCNPJ = document.querySelector(".inputCNPJ")
const inputEmail = document.querySelector(".inputEmail")
const inputState = document.querySelector(".inputState")
const inputCountry = document.querySelector(".inputCountry")
const inputCEP = document.querySelector(".inputCEP")
const inputDescription = document.querySelector(".description")
const inputTelefone = document.querySelector(".inputTelefone")

const erro = document.querySelector(".erro")

const checkboxJava = document.querySelector(".checkboxJava")
const checkboxGroovy = document.querySelector(".checkboxGroovy")
const checkboxJavaScript = document.querySelector(".checkboxJavaScript")
const checkboxMysql = document.querySelector(".checkboxMysql")
const checkboxPython = document.querySelector(".checkboxPython")
const checkboxSpring = document.querySelector(".checkboxSpring")

function criaEmpresa(){
    let skillsNecessarias:Array<string> = new Array<string>()
    if (checkboxJava.checked) skillsNecessarias.push("Java")
    if (checkboxGroovy.checked) skillsNecessarias.push("Groovy")
    if (checkboxJavaScript.checked) skillsNecessarias.push("JavaScript")
    if (checkboxMysql.checked) skillsNecessarias.push("MySql")
    if (checkboxPython.checked) skillsNecessarias.push("Python")
    if (checkboxSpring.checked) skillsNecessarias.push("Spring")

    console.log(skillsNecessarias)

    const empresa = new JuridicPerson(
        inputName.value,
        inputEmail.value,
        inputCountry.value,
        inputState.value,
        inputDescription.value,
        skillsNecessarias,
        inputCEP.value,
        inputState.value
    )
    empresas.push(empresa)
    console.log(empresas)
}

function validaDadosEmpresa(){
    const validaCNPJ = /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/
    const validaCEP = /^\d{2}\.?\d{3}\-\d{3}/
    const validaEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/

    var stringErro = " "

    if (!validaCNPJ.test(inputCNPJ.value)){
        stringErro = stringErro + "Insira um CNPJ válido!"
    }

    if (!validaCEP.test(inputCEP.value)){
        stringErro = stringErro + " Insira um CEP válido!"
    }

    if (!validaEmail.test(inputEmail.value)){
        stringErro = stringErro + " Insira um Email válido!"
    }

    if (!(checkboxGroovy.checked || checkboxJava.checked || checkboxJavaScript.checked || checkboxMysql.checked || checkboxSpring.checked || checkboxPython.checked)){
        stringErro = stringErro + " Selecione alguma Skill Necessária!"
    }

    erro.innerHTML = stringErro

    return (validaCNPJ.test(inputCNPJ.value) &&
            validaCEP.test(inputCEP.value) &&
            validaEmail.test(inputEmail.value) &&
            (checkboxGroovy.checked || checkboxJava.checked || checkboxJavaScript.checked || checkboxMysql.checked || checkboxSpring.checked || checkboxPython.checked)
    )
}

function cadastraEmpresa(){
    if(validaDadosEmpresa()){
        erro.style.display = "none"
        criaEmpresa()
    }else{
        erro.style.display = "grid"
    }
}

function criaCandidato(){
    let skills:Array<string> = new Array<string>()
    if (checkboxJava.checked) skills.push("Java")
    if (checkboxGroovy.checked) skills.push("Groovy")
    if (checkboxJavaScript.checked) skills.push("JavaScript")
    if (checkboxMysql.checked) skills.push("MySql")
    if (checkboxPython.checked) skills.push("Python")
    if (checkboxSpring.checked) skills.push("Spring")

    const candidato = new FisicPerson(
        inputName.value,
        inputEmail.value,
        inputCountry.value,
        inputState.value,
        inputDescription.value,
        inputCPF.value,
        skills,
        inputTelefone.value
    )
    candidatos.push(candidato)
    console.log(candidatos)
}

function validaDadosCandidato(){
    const validaCPF = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/
    const validaEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
    const validaTelefone = /^\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\)? ?(?:[2-8]|9[1-9])[0-9]{3}\-?[0-9]{4}$/

    var stringErro = " "

    if (!validaCPF.test(inputCPF.value)){
        stringErro = stringErro + "Insira um CPF válido!"
    }

    if (!validaEmail.test(inputEmail.value)){
        stringErro = stringErro + " Insira um Email válido!"
    }

    if (!validaTelefone.test(inputTelefone.value)){
        stringErro = stringErro + " Insira um Telefone válido!"
    }

    if (!(checkboxGroovy.checked || checkboxJava.checked || checkboxJavaScript.checked || checkboxMysql.checked || checkboxSpring.checked || checkboxPython.checked)){
        stringErro = stringErro + " Selecione alguma Skill!"
    }

    erro.innerHTML = stringErro

    return (validaCPF.test(inputCPF.value) &&
        validaEmail.test(inputEmail.value) &&
        validaTelefone.test(inputTelefone.value) &&
        (checkboxGroovy.checked || checkboxJava.checked || checkboxJavaScript.checked || checkboxMysql.checked || checkboxSpring.checked || checkboxPython.checked)
    )
}

function cadastraCandidato(){
    if(validaDadosCandidato()){
        erro.style.display = "none"
        criaCandidato()
    }else{
        erro.style.display = "grid"
    }
}

function removeEmpresa(empresa){
    const index = empresas.indexOf(empresa)
    empresas.splice(index, 1)
}

function removeCandidato(candidato){
    const index = candidatos.indexOf(candidato)

    candidatos.splice(index, 1)
}

function listaEmpresas(){

}

function listaCandidatos(){
    const listaCandidatos = document.querySelector(".listaCandidatos")
    const botao = document.querySelector(".buttonAdd")
    listaCandidatos.removeChild(botao)

    for(let cand of candidatos){
        const candidato = document.createElement("div")
        candidato.classList.add("candidato")
        const nomeCandidato = document.createElement("p")
        nomeCandidato.textContent = "*********"
        const descricao = document.createElement("p")
        descricao.textContent = cand.description
        const skills = document.createElement("p")
        skills.textContent = cand.skills

        const botaoExcluir = document.createElement("button");
        botaoExcluir.textContent = "Excluir";
        botaoExcluir.classList.add("botaoExcluir");
        botaoExcluir.addEventListener("click", () => removeCandidato(candidato));

        candidato.appendChild(nomeCandidato)
        candidato.appendChild(descricao)
        candidato.appendChild(skills)
        candidato.appendChild(botaoExcluir)

        listaCandidatos.appendChild(candidato)

        console.log(cand)

    }
}

function listaEmpresas() {
    const listaEmpresas = document.querySelector(".listaEmpresas")
    const botao = document.querySelector(".buttonAdd")
    listaEmpresas.removeChild(botao)

    for (let empr of empresas) {
        const empresa = document.createElement("div")
        empresa.classList.add("empresa")
        const nomeEmpresa = document.createElement("p")
        nomeEmpresa.textContent = "*********"
        const descricao = document.createElement("p")
        descricao.textContent = empr.description
        const necessaryskills = document.createElement("p")
        necessaryskills.textContent = empr.necessarySkils

        const botaoExcluir = document.createElement("button");
        botaoExcluir.textContent = "Excluir";
        botaoExcluir.classList.add("botaoExcluir");
        botaoExcluir.addEventListener("click", () => removeEmpresa(empresa));

        empresa.appendChild(nomeEmpresa)
        empresa.appendChild(descricao)
        empresa.appendChild(necessaryskills)
        empresa.appendChild(botaoExcluir)

        listaEmpresas.appendChild(empresa)

        console.log(empr)

    }
}