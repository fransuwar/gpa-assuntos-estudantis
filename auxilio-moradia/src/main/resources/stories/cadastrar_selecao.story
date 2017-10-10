Narrativa: Como Coordenador
		   Gostaria de cadastrar um seleção,
		   Então conseguirei gerenciar a seleção cadastrada.


Scenario: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Given o usuário acessa a página inicial do sistema acessando pelo endereço http://localhost:8080/selecao
And efetua o login no sistema com o usuário 11111111101 e senha 1234
And o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
And o sistema redireciona para a página de cadastro pelo endereço http://localhost:8080/selecao/cadastrar
When o usuário seleciona o tipo de seleção Auxílio Emergencial | Auxílio Moradia | Iniciação Acadêmica
And preenche o campo Ano
And preenche o campo Início das Inscrições
And preenche o campo Término das Inscrições
And preenche o campo Vagas
And clica no botão salvar
Then o sistema deve exibir a mensagem Seleção cadastrada com sucesso

Scenario: #02 - Cancelar cadastro da seleção

Given o usuário acessa a página inicial do sistema acessando pelo endereço "http://localhost:8080/selecao"
And efetua o login no sistema com o usuário 11111111101 e senha 1234
And o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
And o sistema redireciona para a página de cadastro pelo endereço http://localhost:8080/selecao/cadastrar
When o usuário cancela o cadastro clicando no botão cancelar
Then o sistema redireciona para a página de listagem das seleções cadastradas pelo endereço http://localhost:8080/selecao/listar/ 

Scenario: #03 - Realizar cadastro da Seleção sem preencher campos obrigatórios

Given o usuário acessa a página inicial do sistema acessando pelo endereço "http://localhost:8080/selecao"
And efetua o login no sistema com o usuário 11111111101 e senha 1234
And o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
And o sistema redireciona para a página de cadastro pelo endereço http://localhost:8080/selecao/cadastrar
When o usuário seleciona o tipo de seleção Auxílio Emergencial | Auxílio Moradia | Iniciação Acadêmica
And não preenche o campo Ano
And não preenche o campo Início das Inscrições
And não preenche o campo Término das Inscrições
And clica no botão salvar
Then o sistema deve exibir a mensagem Preencha todos os campos obrigatórios

Scenario: #04 - Realizar cadastro de uma seleção já existente

Given o usuário acessa a página inicial do sistema acessando pelo endereço "http://localhost:8080/selecao"
And efetua o login no sistema com o usuário 11111111101 e senha 1234
And o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
And o sistema redireciona para a página de cadastro pelo endereço http://localhost:8080/selecao/cadastrar
When o usuário seleciona o tipo de seleção Auxílio Moradia
And preenche o campo Ano com o valor 2017
And preenche o campo Início das Inscrições com o valor 09/10/2017
And preenche o campo Término das Inscrições com o valor 11/10/2017
And preenche o campo Vagas com o valor 10
And clica no botão salvar
Then o sistema deve exibir a mensagem Seleção já existente

Scenario: #05 - Realizar cadastro da Seleção com data início posterior a data término ou com a data término anterior a data início

Given o usuário acessa a página inicial do sistema acessando pelo endereço "http://localhost:8080/selecao"
And efetua o login no sistema com o usuário 11111111101 e senha 1234
And o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
And o sistema redireciona para a página de cadastro pelo endereço http://localhost:8080/selecao/cadastrar
When o usuário seleciona o tipo de seleção Auxílio Emergencial | Auxílio Moradia | Iniciação Acadêmica
And preenche o campo Ano
And preenche o campo Início das Inscrições com o valor 20/10/2017
And preenche o campo Término das Inscrições com o valor 19/10/2017
And preenche o campo Vagas
And clica no botão salvar
Then o sistema deve exibir a mensagem Data informada é inválida

