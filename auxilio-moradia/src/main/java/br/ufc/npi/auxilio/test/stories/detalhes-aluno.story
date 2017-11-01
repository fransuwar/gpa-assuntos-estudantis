Narrative: Como servidor gostaria de visualizar as 
            informações referentes às inscrições e 
            documentos enviados pelo aluno.

@Meta
@Author: Afonso Neto
@Data: 23/10/2017

Scenario:
1-Visualizar informação da seleção do tipo Auxílio Moradia
Given o usuário esteja logado no sistema
When clicar sobre a seleção 67
When clicar sobre o aluno 29
Then titulo da pagina é Auxílio - Detalhes da Inscricao
Then nome completo é Mariana Lima
Then cpf é email é 33333333301
Then email é afonsoneto121@gmail.com

Scenario: 
2-Visualizar informação da seleção do tipo Iniciação Acadêmica
Given o usuário esteja logado no sistema
When clicar sobre a seleção 87
When clicar sobre o aluno 32
Then titulo da pagina é Auxílio - Detalhes da Inscricao
Then nome completo é Mariana Lima
Then cpf é email é 33333333301
Then email é afonsoneto121@gmail.com

Scenario: 
3-Visualizar informação da seleção do tipo Auxílio Emergencial
Given o usuário esteja logado no sistema
When clicar sobre a seleção 103
When clicar sobre o aluno 42
Then titulo da pagina é Auxílio - Detalhes da Inscricao
Then nome completo é Guilherme Tavares
Then cpf é email é 33333333302
Then email é a@a.com

Scenario: 
4-Visualização dos documentos
Given o usuário esteja logado no sistema
When clicar sobre a seleção 67
When clicar sobre análise dos documento 29
Then titulo da pagina é Enviar Documentação
Then os documentos Identidade e CPF são visiveis

Scenario: 
5-Download dos documentos
Given o usuário esteja logado no sistema
When clicar sobre a seleção 67
When clicar sobre análise dos documento 29
When clicar sobre download do arquivo Identidade 233
Then é baixado o arquivo Screenshot-2017-10-30 Auxílio - Detalhes da Inscricao.png