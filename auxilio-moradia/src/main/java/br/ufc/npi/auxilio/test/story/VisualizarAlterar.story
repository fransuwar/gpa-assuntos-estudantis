Story: Como servidor gostaria de visualizar/alterar o parecer final da seleção.

Cenário: O Parecer final deferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de analiseDocumentação seja deferido
E entrevista seja deferido
E visitaDomiciliar seja deferido
Então o resultado de parecer final seja deferido

Cenário: O Parecer final indeferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de AnaliseDocumentação seja indeferido
Ou entrevista seja indeferido
Ou visitaDomiciliar seja indeferido
Então o resultado de parecer final seja indeferido

Cenário: O Parecer final quando um dos resultados ainda não foi avaliado
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de AnaliseDocumentação seja não avaliado
Ou Entrevista seja não avaliado
Ou VisitaDomiciliar seja não avaliado
Então o resultado de parecer final seja indeferido

Cenário: Quando o ícone é verde para parecer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja deferido
Então a cor do ícone deve ser verde

Cenário: Quando o ícone é vermelho para parcer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja indeferido
Então a cor do ícone deve ser vermelho

Cenário: Quando o ícone é verde para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja deferido
Então a cor do ícone deve ser verde

Cenário: Quando o ícone é vermelho para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja indeferido
Então a cor do ícone deve ser vermelho

Cenário: Quando o ícone é verde para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja deferido
Então a cor do ícone deve ser verde

Cenário: Quando o ícone é vermelho para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja indeferido
Então a cor do ícone deve ser vermelho

Cenário: Quando o ícone é verde para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja deferido
Então a cor do ícone deve ser verde

Cenário: Quando o ícone é vermelho para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja indeferido
Então a cor do ícone deve ser vermelho

Cenário: Quando exibir o formulário do parecer final
Dado que um servidor clique no botão referente ao formulário de parecer final
Quando o botão referente ao formulário de parecer final é clicado
Então uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo

Cenário: Quando não exibir o formulário do parecer final
Dado que um servidor clique em parecer final
Quando o parecer final é clicado
Então nada é para ser exibido além do que já estava sendo exibido

Cenário: Quando o formulário de parcer final for exibido
Dado que servidor deseje visualizar o formulário referente ao de parecer final
Quando o formulário é exibido
Então os campos de edição que o servidor pode editar são resultado e observações

Cenário: Quando é feita uma alteração no campo de observações no formulário de parecer final
Dado que um servidor deseje alterar os dados do campo de observações do formulário
Quando as alterações no campo de observações é feita e o botão de salvar é clicado
Então o campo do formulário referente a observações é alterado
E a tela de inscições para aquela seleção é mostrada novamente

Cenário: Quando é feita uma alteração no campo de resultado no formulário de parecer final
Dado que um servidor deseje alterar os dados do campo de resultado do formulário
Quando a alteração no campo de resultado é feita e o botão de salvar é clicado
Então o campo do formulário referente a resultado não é alterado
E a tela de inscições para aquela seleção é mostrada novamente