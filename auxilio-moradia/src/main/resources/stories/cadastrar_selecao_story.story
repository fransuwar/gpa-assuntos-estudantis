Narrativa: Como Coordenador
		   Gostaria de cadastrar um seleção,
		   Então conseguirei gerenciar a seleção cadastrada.


Cenário: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Dado que o usuário acessa a página de login do sistema
E efetua o login no sistema com o login e senha
E o sistema redireciona para a página principal do sistema
E o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
E o sistema redireciona para a página de cadastro
Quando o usuário seleciona o tipo de seleção
E preenche o campo Ano
E preenche o campo Início das Inscrições
E preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema redireciona para a página de detalhes da seleção cadastrada
E o sistema deve exibir uma mensagem

Cenário: #02 - Cancelar cadastro da seleção

Dado que o usuário acessa a página de login do sistema
E efetua o login no sistema com o login e senha
E o sistema redireciona para a página principal do sistema
E o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
E o sistema redireciona para a página de cadastro
Quando o usuário cancela o cadastro clicando no botão cancelar
Então o sistema redireciona para a página de listagem das seleções cadastradas

Cenário: #03 - Realizar cadastro da Seleção sem preencher campos obrigatórios

Dado que o usuário acessa a página de login do sistema
E efetua o login no sistema com o login e senha
E o sistema redireciona para a página principal do sistema
E o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
E o sistema redireciona para a página de cadastro
Quando o usuário seleciona o tipo de seleção
E não preenche o campo Ano
E não preenche o campo Início das Inscrições
E não preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema deve exibir uma mensagem

Cenário: #04 - Realizar cadastro de uma seleção já existente

Dado que o usuário acessa a página de login do sistema
E efetua o login no sistema com o login e senha
E o sistema redireciona para a página principal do sistema
E o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
E o sistema redireciona para a página de cadastro
Quando o usuário seleciona o tipo de seleção
E preenche o campo Ano
E preenche o campo Início das Inscrições
E preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema deve exibir uma mensagem

Cenário: #05 - Realizar cadastro da Seleção com data início posterior a data término ou com a data término anterior a data início

Dado que o usuário acessa a página de login do sistema
E efetua o login no sistema com o login e senha
E o sistema redireciona para a página principal do sistema
E o usuário clica no botão Nova Seleção para cadastrar uma nova Seleção
E o sistema redireciona para a página de cadastro
Quando o usuário seleciona o tipo de seleção
E preenche o campo Ano
E preenche o campo Início das Inscrições
E preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema deve exibir uma mensagem

