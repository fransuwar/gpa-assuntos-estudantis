Narrativa: 
Como um Coordenador
Desejo cadastrar um seleção,
De modo que conseguirei gerenciar a seleção cadastrada.


@Meta
@Author: Lucas Vieira
@Data: 19/10/2017

Cenário: #01 - Realizar cadastro da seleção com o tipo Auxílio Emergencial

Dado que o usuário acessa a página de cadastro de seleções
Quando o usuário seleciona o tipo de seleção
E preenche o campo Ano
E preenche o campo Início das Inscrições
E preenche o campo Término das Inscrições
E preenche o campo Vagas
E clica no botão salvar
Então o sistema redireciona para a página de detalhes
E o sistema deve exibir uma mensagem

