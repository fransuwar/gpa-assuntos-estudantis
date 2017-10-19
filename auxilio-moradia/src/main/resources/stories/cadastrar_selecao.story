Narrativa: Como Coordenador
		   Gostaria de cadastrar um seleção,
		   Então conseguirei gerenciar a seleção cadastrada.


Cenário: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Dado que o usuario se loga
Quando o usuário seleciona o tipo de seleção
E preenche o campo Ano
E preenche o campo Início das Inscrições
E preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema deve exibir uma mensagem

