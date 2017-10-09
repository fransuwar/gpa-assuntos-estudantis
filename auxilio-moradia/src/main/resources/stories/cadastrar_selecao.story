Narrativa: Como Coordenador
		   Gostaria de cadastrar um seleção,
		   Então conseguirei gerenciar a seleção cadastrada.


Cenário: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário seleciona o tipo de seleção Auxílio Emergencial
E preenche os demais campos e clica no botão salvar
Então deve exibir a mensagem Seleção cadastrada com sucesso
E seleção cadastrada deve ter tipo de seleção Auxílio Emergencial

Cenário: #02 - Realizar cadastro da seleção com o tipo Auxílio Moradia

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário seleciona o tipo de seleção Auxílio Moradia
E preenche os demais campos e clica no botão salvar
Então deve exibir a mensagem Seleção cadastrada com sucesso
E seleção cadastrada deve ter tipo de seleção Auxílio Moradia

Cenário: #03 - Realizar cadastro da seleção com o tipo Iniciação Acadêmica

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário seleciona o tipo de seleção Iniciação Acadêmica
E preenche os demais campos e clica no botão salvar
Então deve exibir a mensagem Seleção cadastrada com sucesso
E seleção cadastrada deve ter tipo de seleção Iniciação Acadêmica

Cenário: #04 - Cancelar cadastro de seleção

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário preenche todos os campos necessários ou não
E deseja cancelar o cadastro clicando no botão cancelar
Então o sistema redireciona para a página inicial 

Cenário: #05 - Realizar cadastro sem preencher campos obrigatórios

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário não preenche os campos ano, inicio, termino
E clica no botão salvar
Então o sistema deve exibir a mensagem Preencha todos os campos obrigatórios

Cenário: #06 - Realizar cadastro de seleção já existente

Dado que o usuário acessa a página inicial do sistema e acessando pelo endereço "http://localhost:8080/selecao"
E efetua o login no sistema com o usuário $usuario e senha $senha
E o usuário acessa a opção $opcao no menu
Quando o usuário preenche os campos necessários com valores idênticos de uma seleção já cadastrada
E clica no botão salvar
Então o sistema deve exibir a mensagem Seleção já existente


