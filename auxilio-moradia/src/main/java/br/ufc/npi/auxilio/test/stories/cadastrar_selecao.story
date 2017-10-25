Narrative: 
As a Coordenador
I want to cadastrar um seleção,
So that conseguirei gerenciar a seleção cadastrada.


@Meta
@Author: Lucas Vieira
@Data: 19/10/2017


Scenario: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Given o usuário acessa a página de cadastro de seleções
When o usuário seleciona o tipo de seleção <selecao>
And preenche o campo Ano com o valor <ano>
And preenche o campo Início das Inscrições com a data <dataInicio> 
And preenche o campo Término das Inscrições com a data <dataTermino> 
And preenche o campo Vagas com o valor <vagas>
And clica no botão salvar
Then o sistema deve exibir a seguinte mensagem <mensagem>

Examples:
|selecao|ano|dataInicio|dataTermino|vagas|mensagem| 
|Auxílio Moradia|2017|27/10/2017|28/10/2017|10|Seleção cadastrada com sucesso|

Scenario: #02 - Cancelar cadastro da seleção

Given o usuário acessa a página de cadastro de seleções
When o usuário cancela o cadastro clicando no botão cancelar
Then o sistema redireciona para a página de listagem das seleções cadastradas

Scenario: #03 - Realizar cadastro da Seleção sem preencher campos obrigatórios

Given o usuário acessa a página de cadastro de seleções
When o usuário seleciona o tipo de seleção <selecao>
And preenche o campo Ano com o valor <ano>
And não preenche o campo Início das Inscrições
And não preenche o campo Término das Inscrições
And preenche o campo Vagas com o valor <vagas>
And clica no botão salvar
Then o sistema deve exibir a seguinte mensagem <mensagem>

Examples:
|selecao|ano|vagas|mensagem| 
|Auxílio Moradia|2017|10|Preencha todos os campos obrigatórios|

Scenario: #04 - Realizar cadastro da Seleção com data início posterior a data término

Given o usuário acessa a página de cadastro de seleções
When o usuário seleciona o tipo de seleção <selecao>
And preenche o campo Ano com o valor <ano>
And preenche o campo Início das Inscrições com a data <dataInicio>
And preenche o campo Término das Inscrições com a data <dataTermino>
And preenche o campo Vagas com o valor <vagas>
And clica no botão salvar
Then o sistema deve exibir a seguinte mensagem <mensagem>

Examples:
|selecao|ano|dataInicio|dataTermino|vagas|mensagem| 
|Auxílio Moradia|2017|28/10/2017|27/10/2017|10|A data de término das inscrições deve ser posterior à data de início|

