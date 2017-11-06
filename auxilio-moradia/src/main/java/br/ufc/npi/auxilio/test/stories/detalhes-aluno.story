Narrativa: Como servidor gostaria de visualizar as 
            informações referentes às inscrições e 
            documentos enviados pelo aluno.

@Meta
@Author: Afonso Neto
@Data: 23/10/2017

Cenário:
1-Visualizar informação da seleção do tipo Auxílio Moradia
Dado que o usuário esteja logado no sistema
Quando clicar sobre a seleção 67
Quando clicar sobre o aluno 29
Então titulo da pagina é Auxílio - Detalhes da Inscricao
Então nome completo é Mariana Lima
Então cpf é email é 33333333301
Então email é afonsoneto121@gmail.com

Cenário: 
2-Visualizar informação da seleção do tipo Iniciação Acadêmica
Dado que o usuário esteja logado no sistema
Quando clicar sobre a seleção 87
Quando clicar sobre o aluno 32
Então titulo da pagina é Auxílio - Detalhes da Inscricao
Então nome completo é Mariana Lima
Então cpf é email é 33333333301
Então email é afonsoneto121@gmail.com

Cenário: 
3-Visualizar informação da seleção do tipo Auxílio Emergencial
Dado que o usuário esteja logado no sistema
Quando clicar sobre a seleção 103
Quando clicar sobre o aluno 42
Então titulo da pagina é Auxílio - Detalhes da Inscricao
Então nome completo é Guilherme Tavares
Então cpf é email é 33333333302
Então email é a@a.com

Cenário: 
4-Visualização dos documentos
Dado que o usuário esteja logado no sistema
Quando clicar sobre a seleção 67
Quando clicar sobre análise dos documento 29
Então titulo da pagina é Enviar Documentação
Então os documentos Identidade e CPF são visiveis

Cenário: 
5-Download dos documentos
Dado que o usuário esteja logado no sistema
Quando clicar sobre a seleção 67
Quando clicar sobre análise dos documento 29
Quando clicar sobre download do arquivo Identidade 233
Então é baixado o arquivo Screenshot-2017-10-30 Auxílio - Detalhes da Inscricao.png