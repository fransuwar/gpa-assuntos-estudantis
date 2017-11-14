Narrativa: 
Como um Coordenador
Desejo visualizar a inscrição da seleção,
De modo que conseguirei gerenciar a seleção selecionada.


@Meta
@Author: Joao Victor
@Data: 23/10/2017

Cenário: #01 - Exportar Resultado dos alunos deferidos existentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Deferido
Quando clico no botão Exportar para pdf dos alunos deferidos
Então o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos deferidos para o computador

Cenário: #02 - Exportar Resultado dos alunos indeferidos existentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Indeferido
Quando clico no botão Exportar para pdf dos alunos indeferidos
Então o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos indeferidos para o computador

Cenário: #03 - Exportar Resultado dos alunos reservas existentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Reserva
Quando clico no botão Exportar para pdf dos alunos reservas
Então o sistema baixa um arquivo 'Resultado Final.pdf' dos alunos reservas para o computador

Cenário: #04 - Exportar Resultado dos alunos deferidos inexistentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Reserva
Quando clico no botão Exportar para pdf dos alunos reservas
Então o sistema exibe uma mensagem Nenhum Aluno selecionado

Cenário: #05 - Exportar Resultado dos alunos indeferidos inexistentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Reserva
Quando clico no botão Exportar para pdf dos alunos reservas
Então o sistema exibe uma mensagem Nenhum Aluno indeferido

Cenário: #06 - Exportar Resultado dos alunos reservas inexistentes na seleção

Dado que o usuário acessa a página de visualizar inscrição da seleção
Quando o usuário clica no botão Menu
Quando clico no botão Exibir Resultado Final
Quando clico no botão Reserva
Quando clico no botão Exportar para pdf dos alunos reservas
Então o sistema exibe uma mensagem Nenhum Aluno na reserva