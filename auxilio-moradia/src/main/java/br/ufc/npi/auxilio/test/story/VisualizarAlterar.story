Story: Como servidor gostaria de visualizar/alterar o parecer final da seleção.

@Meta
@Autor: Amarildo Barros
@Data: 19/10/2017

Cenário: O Parecer final deferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de Análise Documentação, Entrevista e Visita Domiciliar forem <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|valor|valorParecer|
|Deferido|Deferido|

Cenário: O Parecer final indeferido
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de <documentação> seja <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|documentação|valor|valorParecer|
|AnaliseDocumentacao|Indeferido|Indeferido|
|Entrevista|Indeferido|Indeferido|
|VisitaDomiciliar|Indeferido|Indeferido|

Cenário: O Parecer final quando um dos resultados ainda não foi avaliado
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado de <documentação> seja <valor>
Então o resultado do Parecer Final será <valorParecer>

Exemplos:
|documentação|valor|valorParecer|
|AnaliseDocumentacao|Não Avaliado|Indeferido|
|Entrevista|Não Avaliado|Indeferido|
|VisitaDomiciliar|Não Avaliado|Indeferido|

Cenário: Quando o ícone é verde para parecer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: Quando o ícone é vermelho para parcer final
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado do parecer final seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: Quando o ícone é verde para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: Quando o ícone é vermelho para resultado de AnaliseDocumentação
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da AnaliseDocumentação seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: Quando o ícone é verde para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: Quando o ícone é vermelho para resultado de Entrevista
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da Entrevista seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: Quando o ícone é verde para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja deferido
Então a cor do ícone será <verde>

Exemplos:
|verde|
|#66BB6A|

Cenário: Quando o ícone é vermelho para resultado de VisitaDomiciliar
Dado que um servidor visualize inscrições de uma seleção
Quando o resultado da VisitaDomiciliar seja indeferido
Então a cor do ícone deve ser <vermelho>

Exemplos:
|vermelho|
|#EF5350|

Cenário: Quando exibir o formulário do parecer final
Dado que um servidor possa clicar no botão referente ao formulário de parecer final
Quando o botão referente ao formulário de parecer final é clicado
Então uma tela é exibida referente ao formulário de parecer final possibilitando alterações sobre o mesmo