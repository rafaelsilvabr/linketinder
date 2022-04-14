"use strict";
const a = "Teste3";
console.log(a);
class Person {
    constructor(name, email, country, state, description) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.state = state;
        this.description = description;
    }
}
class JuridicPerson extends Person {
    constructor(name, email, country, state, description, cnpj, cep, necessarySkils) {
        super(name, email, country, state, description);
        this.cnpj = cnpj;
        this.cep = cep;
        this.necessarySkils = necessarySkils;
    }
}
class FisicPerson extends Person {
    constructor(name, email, country, state, description, cpf, skills) {
        super(name, email, country, state, description);
        this.cpf = cpf;
        this.skills = skills;
    }
}
let empresas = new Array();
let candidatos = new Array();
const candidato1 = new FisicPerson("Joao", "joaozinho@gmail.com", "Brasil", "GO", "Um programador iniciante", "123456789564", ["java", "groovy"]);
const candidato2 = new FisicPerson("Fernando", "fernandinho@gmail.com", "Brasil", "SP", "Um programador veterano", "78988456821", ["angular", "javascript"]);
candidatos.push(candidato1);
candidatos.push(candidato2);
const empresa1 = new JuridicPerson("Empresa01", "company@mail.com", "Brasil", "GO", "Uma Empresa legal");
const empresa2 = new JuridicPerson("Empresa02", "company2@mail.com", "Brasil", "RJ", "Uma Empresa chata");
empresas.push(empresa1);
empresas.push(empresa2);
const inputName = document.querySelector(".inputName");
const inputCPF = document.querySelector(".inputCPF");
const inputCNPJ = document.querySelector(".inputCNPJ");
const inputEmail = document.querySelector(".inputEmail");
const inputState = document.querySelector(".inputState");
const inputCountry = document.querySelector(".inputCountry");
const inputCEP = document.querySelector(".inputCEP");
const inputDescription = document.querySelector(".description");
const inputSkills = document.querySelector(".skills");
const inputNecessarySkills = document.querySelector(".necessarySkills");
function cadastraEmpresa() {
    const empresa = new JuridicPerson(inputName.value, inputEmail.value, inputCountry.value, inputState.value, inputDescription.value, inputNecessarySkills.value, inputCEP.value, inputState.value);
    empresas.push(empresa);
    console.log(empresas);
}
function cadastraCandidato() {
    const candidato = new FisicPerson(inputName.value, inputEmail.value, inputCountry.value, inputState.value, inputDescription.value, inputCPF.value, inputSkills.value);
    candidatos.push(candidato);
    console.log(candidatos);
}
function removeEmpresa(empresa) {
    const index = empresas.indexOf(empresa);
    empresas.splice(index, 1);
}
function removeCandidato(candidato) {
    const index = candidatos.indexOf(candidato);
    candidatos.splice(index, 1);
}
function listaEmpresas() {
}
function listaCandidatos() {
    const listaCandidatos = document.querySelector(".listaCandidatos");
    const botao = document.querySelector(".buttonAdd");
    listaCandidatos.removeChild(botao);
    for (let cand of candidatos) {
        const candidato = document.createElement("div");
        candidato.classList.add("candidato");
        const nomeCandidato = document.createElement("p");
        nomeCandidato.textContent = "*********";
        const descricao = document.createElement("p");
        descricao.textContent = cand.description;
        const skills = document.createElement("p");
        skills.textContent = cand.skills;
        const botaoExcluir = document.createElement("button");
        botaoExcluir.textContent = "Excluir";
        botaoExcluir.classList.add("botaoExcluir");
        botaoExcluir.addEventListener("click", () => removeCandidato(candidato));
        candidato.appendChild(nomeCandidato);
        candidato.appendChild(descricao);
        candidato.appendChild(skills);
        candidato.appendChild(botaoExcluir);
        listaCandidatos.appendChild(candidato);
        console.log(cand);
    }
}
function listaEmpresas() {
    const listaEmpresas = document.querySelector(".listaEmpresas");
    const botao = document.querySelector(".buttonAdd");
    listaEmpresas.removeChild(botao);
    for (let empr of empresas) {
        const empresa = document.createElement("div");
        empresa.classList.add("empresa");
        const nomeEmpresa = document.createElement("p");
        nomeEmpresa.textContent = "*********";
        const descricao = document.createElement("p");
        descricao.textContent = empr.description;
        const necessaryskills = document.createElement("p");
        necessaryskills.textContent = empr.necessarySkils;
        const botaoExcluir = document.createElement("button");
        botaoExcluir.textContent = "Excluir";
        botaoExcluir.classList.add("botaoExcluir");
        botaoExcluir.addEventListener("click", () => removeEmpresa(empresa));
        empresa.appendChild(nomeEmpresa);
        empresa.appendChild(descricao);
        empresa.appendChild(necessaryskills);
        empresa.appendChild(botaoExcluir);
        listaEmpresas.appendChild(empresa);
        console.log(empr);
    }
}
