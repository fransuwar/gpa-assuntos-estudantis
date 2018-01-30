Narrativa: 
Como um Coordenador
Desejo cadastrar um seleção,
De modo que conseguirei gerenciar a seleção cadastrada.

Cenário: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Dado que o usuário acessa a página de cadastro de seleções
Quando o usuário seleciona o tipo de seleção <selecao>
Quando o usuário preenche o campo Ano com o valor <ano>
Quando o usuário preenche o campo Início das Inscrições com a data <dataInicio> 
Quando o usuário preenche o campo Término das Inscrições com a data <dataTermino> 
Quando o usuário preenche o campo Vagas com o valor <vagas>
Quando o usuário clica no botão salvar
Então o sistema deve exibir a seguinte mensagem <mensagem>

Exemplos:
|selecao|ano|dataInicio|dataTermino|vagas|mensagem| 
|Auxílio Moradia|2017|27/10/2017|28/10/2017|10|Seleção cadastrada com sucesso|
|Auxílio Emergencial|2017|27/10/2017|28/10/2017|10|Seleção cadastrada com sucesso|
|Iniciação Acadêmica|2017|27/10/2017|28/10/2017|10|Seleção cadastrada com sucesso|

Cenário: #02 - Cancelar cadastro da seleção

Dado que o usuário acessa a página de cadastro de seleções
Quando o usuário cancela o cadastro clicando no botão cancelar
Então o sistema redireciona para a página de listagem das seleções cadastradas

Cenário: #03 - Realizar cadastro da Seleção sem preencher campos obrigatórios

Dado que o usuário acessa a página de cadastro de seleções
Quando o usuário seleciona o tipo de seleção <selecao>
Quando o usuário preenche o campo Ano com o valor <ano>
Quando o usuário não preenche o campo Início das Inscrições
Quando o usuário não preenche o campo Término das Inscrições
Quando o usuário preenche o campo Vagas com o valor <vagas>
Quando o usuário clica no botão salvar
Então o sistema deve exibir a seguinte mensagem <mensagem>

Exemplos:
|selecao|ano|vagas|mensagem| 
|Auxílio Moradia|2017|10|Preencha todos os campos obrigatórios|

Cenário: #04 - Realizar cadastro da Seleção com data início posterior a data término

Dado que o usuário acessa a página de cadastro de seleções
Quando o usuário seleciona o tipo de seleção <selecao>
Quando o usuário preenche o campo Ano com o valor <ano>
Quando o usuário preenche o campo Início das Inscrições com a data <dataInicio>
Quando o usuário preenche o campo Término das Inscrições com a data <dataTermino>
Quando o usuário preenche o campo Vagas com o valor <vagas>
Quando o usuário clica no botão salvar
Então o sistema deve exibir a seguinte mensagem <mensagem>

Exemplos:
|selecao|ano|dataInicio|dataTermino|vagas|mensagem| 
|Auxílio Moradia|2017|28/10/2017|27/10/2017|10|A data de término das inscrições deve ser posterior à data de início|

