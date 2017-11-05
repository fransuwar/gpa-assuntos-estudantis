Story: Como servidor gostaria de visualizar/alterar o parecer final da seleção.

@Meta
@Autor: Amarildo Barros
@Data: 19/10/2017

Cenário: #01 - O Parecer final deferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de Análise Documentação, Entrevista e Visita Domiciliar forem <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|valor|valorParecer|
|Deferido|Deferido|

Cenário: #02 - O Parecer final indeferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de <documentação> seja <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|documentação|valor|valorParecer|
|AnaliseDocumentacao|Indeferido|Indeferido|
|Entrevista|Indeferido|Indeferido|
|VisitaDomiciliar|Indeferido|Indeferido|

Cenário: #03 - O Parecer final quando um dos resultados ainda não foi avaliado
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de <documentação> seja <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|documentação|valor|valorParecer|
|AnaliseDocumentacao|Não Avaliado|Indeferido|
|Entrevista|Não Avaliado|Indeferido|
|VisitaDomiciliar|Não Avaliado|Indeferido|

Cenário: #04 - Quando o ícone é verde para parecer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: #05 - Quando o ícone é vermelho para parcer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: #06 - Quando o ícone é verde para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: #07 - Quando o ícone é vermelho para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: #08 - Quando o ícone é verde para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: #09 - Quando o ícone é vermelho para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: #10 - Quando o ícone é verde para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: #11 - Quando o ícone é vermelho para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: #12 - Quando exibir o formulário do parecer final
Dado que um servidor possa clicar no botão referente ao formulário de parecer final
Quando o botão referente ao formulário de parecer final é clicado
Então uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo

Cenário: #13 - Quando não exibir o formulário do parecer final
Dado que um servidor clique em parecer final
Quando o parecer final é clicado
Então nada é para ser exibido além do que já estava sendo exibido

Cenário: #14 - Quando o formulário de parcer final for exibido
Dado que servidor deseje visualizar o formulário referente ao de parecer final
Quando o formulário é exibido
Então os campos de edição que o servidor pode editar são resultado e observações

Cenário: #15 - Quando é feita uma alteração no campo de observações no formulário de parecer final
Dado que um servidor deseje alterar os dados do campo de observações do formulário
Quando o <texto> no campo de observações é feita e o botão de salvar é clicado
Então o campo do formulário referente a observações é alterado de acordo com o <texto>
Então a tela de inscrições para aquela seleção é mostrada novamente

Exemplos:
|texto|
|teste do campo observações|

Cenário: #16 - Quando é feita uma alteração no campo de resultado no formulário de parecer final
Dado que um servidor deseje alterar os dados do campo de resultado do formulário
Quando a alteração no campo de resultado <alteracao> é feita e o botão de salvar é clicado
Então o campo do formulário referente a resultado é alterado de acordo com a <alteracao>
Então a tela de inscrições para aquela seleção é mostrada novamente

Exemplos:
|alteracao|
|DEFERIDO|
|INDEFERIDO|
|NAO_AVALIADO|