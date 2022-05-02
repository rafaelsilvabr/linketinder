package mainPackage

@GrabConfig(systemClassLoader=true)
@Grab(group='org.postgresql', module='postgresql', version='9.4-1205-jdbc42')

import groovy.sql.Sql

class dbcrud{
    String url= 'jdbc:postgresql://localhost:5432/linketinder'
    String user= 'postgres'
    String password= 'postgres'
    String driver= 'org.postgresql.Driver'
    Sql sql

    dbcrud(){
        this.sql= Sql.newInstance(this.url, this.user, this.password, this.driver)
    }

    void close (){
        this.sql.close()
    }

    void list(tabela){
        def result = sql.rows("SELECT * FROM " + tabela)
        for (i in result){
            print(i)
            print("\n")
        }
    }

    void listVagas(JuridicPerson empresa){
        def result = sql.rows("SELECT * FROM vagas WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+ empresa.email +"')")
        for (i in result){
            print(i)
            print("\n")
        }
    }

    void listCompetenciasCandidato(FisicPerson candidato){
        def result = sql.rows("SELECT competencia, candidato FROM competencias_candidatos WHERE candidato = (SELECT id FROM dados_candidatos WHERE email = '"+candidato.email+"')")
        for (i in result){
            print(i)
            print("\n")
        }
    }

    FisicPerson selecionaCandidato(String email){
        def result = sql.firstRow("SELECT * FROM dados_candidatos WHERE email = '"+email+"';")
        FisicPerson candidato = new FisicPerson(
                name: result['nome'],
                sobrenome: result['sobrenome'],
                dataNascimento: result['data_nascimento'],
                email: result['email'],
                cpf: result['cpf'],
                country: result['pais'],
                cep: result['cep'],
                description: result['descricao'],
                password: result['senha']
        )

        def competencias = sql.rows("SELECT * FROM competencias_candidatos WHERE candidato = (SELECT id FROM dados_candidatos WHERE email = '"+candidato.email+"')")
        for (competencia in competencias){
            var nomeCompetencia = sql.firstRow("SELECT nome FROM competencias WHERE id =" + competencia['competencia'])
            candidato.skills.add(nomeCompetencia['nome'])
        }
        return candidato
    }

    JuridicPerson selecionaEmpresa(String email){
        def result = sql.firstRow("SELECT * FROM dados_empresas WHERE email = '"+email+"';")
        JuridicPerson empresa = new JuridicPerson(
                name: result['nome'],
                email: result['email'],
                cnpj: result['cnpj'],
                country: result['pais'],
                cep: result['cep'],
                description: result['descricao'],
                password: result['senha']
        )

        def vagas = sql.rows("SELECT * FROM vagas WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"')")
        for (vaga in vagas){
            Vaga vagaclass = new Vaga(
                    nome: vaga['nome'],
                    descricao: vaga['descricao']
            )
            def competenciasVaga = sql.rows("SELECT * FROM competencias_vagas WHERE vaga = (SELECT id FROM vagas WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"') AND nome = '"+vaga['nome']+"' )")
            //print competenciasVaga
            for (competencia in competenciasVaga){
                var nomeCompetencia = sql.firstRow("SELECT nome FROM competencias WHERE id =" + competencia['competencia'])
                vagaclass.competencias.add(nomeCompetencia['nome'])
            }
            empresa.vagas.add(vagaclass)
        }
        print(empresa)
        return empresa
    }


    void createCandidato(FisicPerson candidato){
        sql.execute("INSERT INTO dados_candidatos(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)" +
                "VALUES ('"+candidato.name+"', '"+candidato.sobrenome+"', '"+candidato.dataNascimento+"','"+candidato.email+"', '"+candidato.cpf+"', '"+candidato.country+"', '"+candidato.cep+"', '"+candidato.description+"', '"+candidato.password+"');")
        for (competencia in candidato.skills){
            createCompetenciaCanidato(competencia, candidato.email)
        }
    }

    void createCompetenciaCanidato(String competencia, String email){
        sql.execute("INSERT INTO competencias_candidatos(competencia, candidato)" +
                "VALUES((SELECT id FROM competencias WHERE nome = '"+competencia+"'), (SELECT id FROM dados_candidatos WHERE email = '"+email+"'));")
    }

    void createEmpresa(JuridicPerson empresa){
        sql.execute("INSERT INTO dados_empresas(nome, cnpj, email, descricao, pais, cep, senha)" +
                "VALUES ('"+empresa.name+"', '"+empresa.cnpj+"', '"+empresa.email+"', '"+empresa.description+"', '"+empresa.country+"', '"+empresa.cep+"', '"+empresa.password+"');")
        for(vaga in empresa.vagas){
            createVaga(vaga, empresa)
        }
    }

    void createCompetencia(String competencia){
        sql.execute("INSERT INTO competencias (nome)" +
                "VALUES ('"+competencia+"');")
    }

    void createVaga(Vaga vaga, JuridicPerson local_vaga){
        sql.execute("INSERT INTO vagas (nome, descricao, local_vaga)" +
                "VALUES ('"+vaga.nome+"', '"+vaga.descricao+"',(SELECT id FROM dados_empresas WHERE email = '"+local_vaga.email+"'));")
        for(competencia in vaga.competencias){
            createCompetenciaVaga(competencia, vaga, local_vaga)
        }
    }

    void createCompetenciaVaga(String competencia, Vaga vaga, JuridicPerson empresa){
        sql.execute("INSERT INTO competencias_vagas (competencia, vaga)\n" +
                "VALUES ((SELECT id FROM competencias WHERE nome = '"+competencia+"'),(SELECT id FROM vagas WHERE nome = '"+vaga.nome+"' AND local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"')));")
    }

    void deleteEmpresa(JuridicPerson empresa){
        sql.execute("DELETE FROM competencias_vagas WHERE vaga = (SELECT id FROM vagas WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"'))")
        sql.execute("DELETE FROM vagas where local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"')")
        sql.execute("DELETE FROM dados_empresas WHERE email = '"+empresa.email+"'")
    }

    void deleteCandidato(FisicPerson candidato){
        sql.execute("DELETE FROM competencias_candidatos WHERE candidato = (SELECT id FROM dados_candidatos WHERE email = '"+candidato.email+"')")
        sql.execute("DELETE FROM dados_candidatos WHERE email = '"+candidato.email+"'")
    }

    void deleteVagaEmpresa(Vaga vaga, JuridicPerson empresa){
        sql.execute("DELETE FROM competencias_vagas WHERE vaga = (SELECT id FROM vagas WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"') AND nome = '"+vaga.nome+"' )")
        sql.execute("DELETE FROM vagas where local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"') AND nome = '"+vaga.nome+"'")
    }

    void updateEmpresa(JuridicPerson empresa){
        sql.execute("UPDATE dados_empresas\n" +
                "SET nome = '"+empresa.name+"'," +
                "cnpj = '"+empresa.cnpj+"'," +
                "pais = '"+empresa.country+"'," +
                "cep = '"+empresa.cep+"'," +
                "descricao = '"+empresa.description+"'," +
                "senha = '"+empresa.password+"'\n" +
                "WHERE email = '"+empresa.email+"';")
    }

    void updateCandidato(FisicPerson candidato){
        sql.execute("UPDATE dados_candidatos\n" +
                "SET nome = '"+candidato.name+"'," +
                "sobrenome = '"+candidato.sobrenome+"'," +
                "data_nascimento = '"+candidato.dataNascimento+"'," +
                "cpf = '"+candidato.cpf+"'," +
                "pais = '"+candidato.country+"'," +
                "cep = '"+candidato.cep+"'," +
                "descricao = '"+candidato.description+"'," +
                "senha = '"+candidato.password+"'\n" +
                "WHERE email = '"+candidato.email+"';")
    }

    void updateCompetencia(String novoNome,String competencia){
        sql.execute("UPDATE competencias\n" +
                "SET nome = '"+novoNome+"'\n" +
                "WHERE nome = '"+competencia+"';")
    }

    void updateVaga(Vaga vaga, JuridicPerson local_vaga){
        sql.execute("UPDATE vagas\n" +
                "SET descricao = '"+vaga.descricao+"'\n" +
                "WHERE local_vaga = (SELECT id FROM dados_empresas WHERE email = '"+empresa.email+"') AND nome = '"+vaga.nome+"';")
    }
}






