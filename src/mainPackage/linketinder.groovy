package mainPackage

import javax.swing.JOptionPane

//TESTE DB

db = new dbcrud()

//db.createCompetencia("Flutter")
//db.updateCandidato()

//db.list("dados_empresas")
db.selecionaEmpresa("paezihosgostosos@gmail.com")

//--------------------

def input = 1
while (input != 0){
    input = JOptionPane.&showInputDialog "BEM VINDO AO LINKETINDER!\n" +
            "O que você deseja listar?\n" +
            "Aperte 1 para listar os candidatos\n" +
            "Aperte 2 para listar as empresas\n" +
            "Aperte 3 para inserir um candidato\n" +
            "Aperte 4 para inserir uma empresa\n" +
            "Aperte 5 para inserir uma competencia\n" +
            "Aperte 6 para inserir uma vaga\n"+
            "Aperte 0 para sair\n"

    if(input == "1"){
        print ("----- candidatos -------\n")
        db.list("dados_candidatos")
        //JOptionPane.showMessageDialog(null,buffer)
    }
    if(input == "2"){
        print("----- empresas -------\n")
        db.list("dados_empresas")
    }
    if(input == "0"){
        break
    }
    if(input != 1 && input != 2){
        JOptionPane.showMessageDialog(null, "INSIRA UMA OPÇÃO VÁLIDA!")
    }
}

//Fecha a conexão com o banco de dados
db.close()