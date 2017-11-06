Narrativa: 
Como um servidor
Desejo alterar a colocação de um aluno na lista de classificação,
De modo que atualizar a lista de classificação.


@Meta
@Author: Francisco Hugo
@Data: 24/10/2017

Cenário: #01 - Verificar se aluno é clicável com parecer final deferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 1 da lista com parecer final DEFERIDO
Então o sistema pode alterar a posição do aluno 1


Cenário: #02 - Verificar se aluno não é clicável com parecer final indeferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 3 da lista com parecer final INDEFERIDO
Então o sistema não pode alterar a posição do aluno 3


Cenário: #03 - Alterar status de selecinados de aluno com parecer final deferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 2 da lista com parecer final DEFERIDO
Quando o usuário clica no toggle button de selecionado do aluno 2
Então o sistema altera o status de selecionado do aluno 2


Cenário: #04 - Alterar status de selecinados de aluno com parecer final indeferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 3 da lista com parecer final INDEFERIDO
Então o sistema mostra como desabilitado o toggle button de selecionado do aluno 3

Cenário: #05 - Altera a colocação de um aluno com parecer final deferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 1 da lista com parecer final DEFERIDO
Quando o usuário seleciona um aluno na posição 2 da lista com parecer final DEFERIDO
Quando o usuário arrasta o aluno 1 para a posição do aluno 2
Então o sistema deve deixar este aluno na posição 2	

Cenário: #06 - Altera a colocação de um aluno com parecer final indeferido

Dado que o usuário acessa a página visualizar inscrições da seleção 52
Quando o usuário seleciona um aluno na posição 2 da lista com parecer final DEFERIDO
Quando o usuário seleciona um aluno na posição 3 da lista com parecer final INDEFERIDO
Quando o usuário arrasta o aluno 3 para a posição do aluno 2
Então o sistema deve deixar este aluno na posição 3

