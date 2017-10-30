Narrative: Como servidor gostaria de visualizar as 
            informações referentes às inscrições e 
            documentos enviados pelo aluno.

Scenario: 1-Visualizar informação da seleção do tipo Auxílio Moradia

Given o usuário esteja logado no sistema com cpf $11111111101 e senha $1234
When clicar sobre a seleção 67
When seleção tenha pelo menos um aluno
When clicar sobre o aluno 
Then as informações sobre Identificação do aluno são mostradas.

Scenario: 2-Visualizar informação da seleção do tipo Iniciação Acadêmica

Given o usuário esteja logado no sistema com cpf $11111111101 e senha $1234
When clicar sobre a seleção 
When seleção tenha pelo menos um aluno
When clicar sobre o aluno 
Then as informações sobre Identificação do aluno são mostradas.

Scenario: 3-Visualizar informação da seleção do tipo Auxílio Emergencial

Given o usuário esteja logado no sistema com cpf $11111111101 e senha $1234
When clicar sobre a seleção 
When seleção tenha pelo menos um aluno
When clicar sobre o aluno 
Then as informações sobre Identificação do aluno são mostradas.

